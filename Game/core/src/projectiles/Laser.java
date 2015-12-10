package projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ship.Player;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-23.
 */
public class Laser extends Weapon{

    Sprite[] length;
    Sprite turn;
    ArrayList<float[]> chain;
    Player player;

    public Laser(int x, int y, double trajectory, Player player){
        super(x,y, trajectory);
        this.sprite = new Sprite(new Texture(("S2.png")));
        this.length = new Sprite[]{new Sprite(new Texture(("S2.png")))};
        this.trajectory = trajectory;
        this.player = player;
        this.x_velo =  Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo =  Math.cos(Math.toRadians(trajectory)) * speed;
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        chain = new ArrayList<float[]>();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void calc(){
        while(onScreen()){
            if
            float x_increment = sprite.getWidth()+(float)x_velo;
            float y_increment = sprite.getHeight()+(float)y_velo;
            this.setBounds(this.getX(), this.getY(), x_increment,  y_increment);
            chain.add(new float[]{x_increment, y_increment, (float)trajectory});

        }

    }

    public boolean onScreen(){
        return true;
    }
    @Override
    void update(){

    }
}
