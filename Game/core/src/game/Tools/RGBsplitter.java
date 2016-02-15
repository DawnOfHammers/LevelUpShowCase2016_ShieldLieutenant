package game.Tools;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Hairuo on 2016-01-21.
 * Supposed to split the required image into its r, g ,b channels
 * May never be used, just a test
 */
public class RGBsplitter {
    static BufferedImage image;
    static BufferedImage red_image, green_image, blue_image;
    static Pixmap pix_map;
    static final int TYPE = BufferedImage.TYPE_INT_ARGB;

    public RGBsplitter(){

    }
    public static void main(String[] args){
        RGBsplitter split = new RGBsplitter();
        split.write("Bullet", "jpg");
    }

    /**
     * public void draw(Texture base){
     * // read image,
     * // change the file name as needed
     * image = base;
     * pix_map = image.getTextureData().consumePixmap();
     * <p>
     * <p>
     * int w = image.getWidth();
     * int h = image.getHeight();
     * <p>
     * // create 3 images for each RGB plane
     * red_image = new Pixmap(w, h, TYPE);
     * green_image = new Pixmap(w, h, TYPE);
     * blue_image = new Pixmap(w, h, TYPE);
     * <p>
     * <p>
     * // split RGB plane
     * for (int y = 0; y < h; y++) {
     * for (int x = 0; x < w; x++) {
     * int pixel = pix_map.getPixel(x, y);
     * <p>
     * int alpha_mask = pixel & 0xff000000;
     * int red = (pixel >> 16) & 0xff;
     * int green = (pixel >> 8) & 0xff;
     * int blue = (pixel) & 0xff;
     * <p>
     * red_image.drawPixel(x, y, alpha_mask | (red << 16));
     * green_image.drawPixel(x, y, alpha_mask | (green << 8));
     * blue_image.drawPixel(x, y, alpha_mask | blue);
     * <p>
     * }
     * }
     * }
     **/

    public void write(String sprite_name, String extension) {
        // read image,
        // change the file name as needed
        try {
            image = ImageIO.read(new File(sprite_name+"."+extension));
        }catch(IOException exception){

        }


        int w = image.getWidth();
        int h = image.getHeight();

        // create 3 images for each RGB plane
        red_image = new BufferedImage(w, h, TYPE);
        green_image = new BufferedImage(w, h, TYPE);
        blue_image = new BufferedImage(w, h, TYPE);


        // split RGB plane
        for (int y = 0; y < h; y++)
            for (int x = 0; x < w; x++) {
                int pixel = image.getRGB(x, y);

                int alpha_mask = pixel & 0xff000000;
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                red_image.setRGB(x, y, alpha_mask | (red << 16));
                green_image.setRGB(x, y, alpha_mask | (green << 8));
                blue_image.setRGB(x, y, alpha_mask | blue);
            }


        // write result to 3 image files
        String format = "png";

        try {ImageIO.write(image, format, new File("Bullet.png"));
            ImageIO.write(red_image, format, new File(sprite_name+"_red.png"));
            ImageIO.write(green_image, format, new File(sprite_name+"_green.png"));
            ImageIO.write(blue_image, format, new File(sprite_name+"_blue.png"));
        }catch(IOException exception){

        }
    }

}
