package entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import entities.shield.Shield;
import entities.ship.player.Player;
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-23.
 */
public class Laser extends Weapon {

    Sprite middle;
    Sprite turn;
    Sprite end;
    ArrayList<float[]> chain;
    ArrayList<double[]> vertices;
    Player player;
    boolean test = true;
    boolean collide = false;
    int length;

    public Laser(int x, int y, double trajectory, GameStage gs) {
        super(x, y, trajectory, gs);
        this.sprite = new Sprite(new Texture(("bullet.jpg")));
        this.middle = new Sprite(new Texture(("S2.png")));
        this.end = new Sprite(new Texture(("S2.png")));

        this.trajectory = trajectory;
        this.player = player;
        this.x_velo = Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo = Math.cos(Math.toRadians(trajectory)) * speed;
        this.vertices = new ArrayList<double[]>();
        this.vertices.add(new double[]{this.getX(), this.getY()});
        chain = new ArrayList<float[]>();

    }

    /**
     * draw method for laser
     * iterates through all pieces and draws them
     *
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        for (float[] i : chain) {
            Sprite draw_sprite = sprite;
            draw_sprite.setRotation(-i[2]);
            draw_sprite.setPosition(i[0], i[1]);

            sprite.setOrigin(0, 0);

            draw_sprite.draw(batch, parentAlpha);


        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        update();
    }

    /**
     * Calculates x y and trajectory of each piece in the laser chain
     *
     * @param game_stage
     */
    public void calc(GameStage game_stage) {
        double cur_x = this.getX();
        double cur_y = this.getY();
        System.out.println(cur_x + "," + cur_y);
        while (onScreen(cur_x, cur_y, game_stage)) {
            for (double[] i : vertices) {

                //double[] f_vert = transform(i[0] + sprite.getWidth(), i[1], trajectory, this.getX(), this.getY());
                double[] s_vert = transform(i[0] , i[1] + sprite.getHeight(), trajectory, cur_x, cur_y);
                //System.out.println(s_vert[0] + "," + s_vert[1]);
                //System.out.println(f_vert[0] + "," + f_vert[1]);
                //System.out.println("b");
                for (Shield shield : game_stage.getPlayer().getShields()) {
                    //double f_trajectory = shield.collideLaser(f_vert[0], f_vert[1], trajectory);

                    double s_trajectory = trajectory;
                    if(!collide) {
                        s_trajectory = shield.collideLaser(s_vert[0], s_vert[1], trajectory);

                    }
                    /*if (f_trajectory < 1000) {
                        trajectory = f_trajectory;
                    } */
                    if (s_trajectory < 1000) {
                        trajectory = s_trajectory;
                        s_vert = transform(i[0] , i[1]+sprite.getHeight(), trajectory, cur_x, cur_y);
                        collide = true;
                    }
                }

                chain.add(new float[]{(float) i[0], (float) i[1], (float) trajectory});

                vertices.set(vertices.indexOf(i),s_vert);
                cur_x = s_vert[0];
                cur_y = s_vert[1];

            }

        }

    }

    /**
     * updates the vertices
     * not really used right now
     * may never be used
     */
    public void updateVertices() {
        for (double[] i : vertices) {
            i = transform(i[0], i[1], trajectory, this.getX(), this.getY());
        }
    }

    /**
     * Performs a rotation matrix on the coordinates given based on the other parameters
     *
     * @param x     the x coordinate to be transformed
     * @param y     the y coordinate to be transformed
     * @param angle the angle which to be rotated
     * @param t_x   the x origin
     * @param t_y   the y origin
     * @return
     */
    public static double[] transform(double x, double y, double angle, double t_x, double t_y) {
        //angle+=90;
        double sin_value = Math.sin(Math.toRadians(angle));
        double cos_value = Math.cos(Math.toRadians(angle));

        //System.out.println(x + "," + y + "lololol");

        x -= t_x;
        y -= t_y;

        //System.out.println(-x * sin_value + y * cos_value + "a");

        double new_x = x * cos_value + y * sin_value;
        double new_y = -x * sin_value + y * cos_value;


        new_x += t_x;
        new_y += t_y;

        //System.out.println(new_x + "," + new_y + "b");




        return new double[]{new_x, new_y};
    }

    /**
     * Checks whether or not the laser is still on the screen
     */
    public boolean onScreen(double x, double y, GameStage game_stage) {
        return !(x > 1000 || y > 1000 || x < -1000 || y < -1000);
    }

    @Override
    protected void update() {
        if(test) {
            calc(gamestage);
            collide = false;
            test = false;

        }

    }

}