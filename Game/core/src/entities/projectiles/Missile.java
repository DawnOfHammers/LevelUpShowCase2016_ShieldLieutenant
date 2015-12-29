package entities.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import entities.ship.player.Player;
import gamestates.playState.GameStage;


/**
 * Created by Hairuo on 2015-11-23.
 */
public class Missile extends Weapon {
    private double turn_speed;

    public Missile(int x, int y, double trajectory, GameStage gs){
        super(x, y, trajectory, gs);
        this.speed = 3;
        this.sprite = new Sprite(new Texture(("bullet.jpg")));
        this.turn_speed = 0.75;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        track(this.gamestage.getPlayer());
        update();
    }

    /**
     * updates the missiles trajectory based on the players position
     * does this by calculating the angle to the player
     * and adjusting its own trajectory accordingly
     * @param player
     */

    public void track(Player player){

        double target_x = player.getX() - this.getX();
        double target_y = player.getY() - this.getY();
        double target_trajectory = (Math.toDegrees(Math.atan2(target_y, target_x))+90+360)%360;




        System.out.println(target_trajectory + "," + trajectory);
        if(Math.abs(trajectory - target_trajectory) < turn_speed){
            trajectory = target_trajectory;
        }else{
            double difference = trajectory - target_trajectory;
            double other_difference = 360 - Math.abs(difference);

            if(Math.abs(other_difference) < Math.abs(difference)){
                target_trajectory += Math.signum(difference)*360;
            }

            if (trajectory < target_trajectory){
                trajectory += turn_speed;
            }else{
                trajectory -= turn_speed;
            }
        }

        trajectory = trajectory%360;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    protected void update() {

        track(gamestage.getPlayer());
        this.x_velo =  Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo =  Math.cos(Math.toRadians(trajectory)) * speed;
        this.setX((float) (this.getX() + x_velo));
        this.setY((float) (this.getY() - y_velo));
        sprite.setPosition(this.getX(),this.getY());
        sprite.setRotation((float)trajectory);
    }

}
