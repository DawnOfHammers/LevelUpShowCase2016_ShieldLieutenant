package entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import entities.ship.player.Player;
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * This class will be the superclass of the bullet class.
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public class Bullet extends Weapon {


    /**
     * Whether or not the bullet as been reflected or not
     */
    protected boolean bounced;

    /**
     * Gets the trajectory of the bullet as a radian angle.
     * @return trajectory
     */

    public float getTrajectory() {
        return (float)trajectory;
    }

    /**
     * Sets the trajectory of the bullet as a radian angle.
     * @param trajectory
     */
    public void setTrajectory(double trajectory) {
        this.trajectory = trajectory;
    }

    /**
     * Constructor for the bullet
     * @param x: initial x position of the bullet
     * @param y: initial y position of the bullet
     */
    public Bullet(int x, int y, double trajectory, GameStage gs, String sprite_name){
        super(x, y, trajectory, gs, sprite_name);
        this.trajectory = trajectory;
        this.speed = 10;
        this.bounced = false;

        sprite.setOrigin(sprite.getWidth() / 2, sprite.getHeight() / 2);
        setBounds(this.getX(), this.getY(), sprite.getWidth(), sprite.getHeight()); //initilization stuff for the actor
        setTouchable(Touchable.enabled);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    /**
     * Update method of the bullet
     *
     * Updates its position and rotation
     */
    @Override
    protected void update() {
        super.update();
        //System.out.println(this.getX()+ "      " + this.getY());
        this.x_velo =  Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo =  Math.cos(Math.toRadians(trajectory)) * speed;
        this.setX((float) (this.getX() + x_velo));
        this.setY((float) (this.getY() + y_velo));
        this.setRotation(360 - (float) trajectory);
        //System.out.println("x: "+this.getX() + " y: "+ this.getY());
        sprite.setPosition(this.getX(), this.getY());
        sprite.setRotation(360 - (float) trajectory);



        if (!onScreen(this.getX(), this.getY(), gamestage)){
            //System.out.println("a");
            this.remove();
            //gamestage.deleteActor(this);
        }
    }

    public boolean isBounced() {
        return bounced;
    }

    public void setBounced(boolean bounced) {
        this.bounced = bounced;
    }

    public boolean onScreen(double x, double y, GameStage game_stage) {
        double delta_x = this.getX() - gamestage.getPlayer().getX();
        double delta_y = this.getY() - gamestage.getPlayer().getY();
        double distance_from_player = Math.hypot(delta_x, delta_y);
        return !(distance_from_player > 1500);
    }

    public ArrayList<double[]> getVertices() {
        return vertices;
    }
}
