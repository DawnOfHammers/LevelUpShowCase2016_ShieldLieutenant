package entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
<<<<<<< HEAD:Game/core/src/entities/projectiles/Laser.java
import entities.ship.player.Player;
=======
import com.mygdx.game.GameStage;
import shield.Shield;
import ship.Player;
>>>>>>> origin/master:Game/core/src/projectiles/Laser.java

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-23.
 */
public class Laser extends Weapon{

    Sprite middle;
    Sprite turn;
    Sprite end;
    ArrayList<float[]> chain;
    ArrayList<double[]> vertices;
    Player player;
    int length;

    public Laser(int x, int y, double trajectory, Player player){
        super(x,y, trajectory);
        this.sprite = new Sprite(new Texture(("S2.png")));
        this.middle = new Sprite(new Texture(("S2.png")));
        this.end = new Sprite(new Texture(("S2.png")));

        this.trajectory = trajectory;
        this.player = player;
        this.x_velo =  Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo =  Math.cos(Math.toRadians(trajectory)) * speed;
        this.vertices = new ArrayList<double[]>();
        this.vertices.add(new double[]{this.getX()+sprite.getWidth(),this.getY()+sprite.getHeight()});
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

    public void calc(GameStage game_stage){
        double cur_x = this.getX();
        double cur_y = this.getY();
        while(onScreen()){
            updateVertices();


            for(double[] i : vertices) {
                

                chain.add(new float[]{(float)i[0], (float)i[1], (float)trajectory});

                i[0] = sprite.getWidth() + (float)cur_x;
                i[1] = sprite.getHeight() + (float)cur_y;

            }

        }

    }

    public void updateVertices(){
        for(double[] i: vertices){
            i = transform(i[0], i[1], trajectory, this.getX(), this.getY());
        }
    }

    public double[] transform(double x, double y, double angle, double t_x, double t_y){
        double sin_value = Math.sin(angle);
        double cos_value = Math.cos(angle);

        x -= t_x;
        y -= t_y;

        x = x*cos_value - y*sin_value;
        y = x*sin_value + y*cos_value;

        x+= t_x;
        y+= t_y;
        return new double[]{x,y};
    }
    /**
    Checks wether or not the laser is still on the screen
     */
    public boolean onScreen(){
        return true;
    }
    @Override
<<<<<<< HEAD:Game/core/src/entities/projectiles/Laser.java
    protected void update(){

=======
    void update(GameStage game_stage){
        calc(game_stage);
>>>>>>> origin/master:Game/core/src/projectiles/Laser.java
    }
}
