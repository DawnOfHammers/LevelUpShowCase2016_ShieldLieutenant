package projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.GameStage;
import ship.Player;

/**
 * Created by Hairuo on 2015-11-23.
 */
public class Missile extends Weapon {
    private double turn_speed;
    public Missile(int x, int y, double trajectory){
        super(x, y, trajectory);
        this.sprite = new Sprite(new Texture(("bullet.jpg")));
        this.turn_speed = 0.5;
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    /**
     * updates the missiles trajectory based on the players position
     * does this by calculating the angle to the player
     * and adjusting its own trajectory accordingly
     * @param player
     */
    public void track(Player player){

        double target_x = player.getOriginX() - this.getX();
        double target_y = player.getOriginY() - this.getY();
        double target_trajectory = Math.atan2(target_y, target_x);

        if (target_trajectory < 0){
            target_trajectory = Math.abs(target_trajectory)+90;
        }else if (target_trajectory < 90){
            target_trajectory = target_trajectory - 90;
        }else{
            target_trajectory = 360- target_trajectory - 90;
        }

        if(trajectory - target_trajectory < turn_speed){
            trajectory = target_trajectory;
        }else if (trajectory < target_trajectory){
            trajectory += turn_speed;
        }else{
            trajectory -= turn_speed;
        }





    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch, parentAlpha);
    }

    @Override
    void update(GameStage game_stage) {
        track(game_stage.getPlayer());
        this.x_velo =  Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo =  Math.cos(Math.toRadians(trajectory)) * speed;
        this.setX((float) (this.getX() + x_velo));
        this.setY((float) (this.getY() + y_velo));
        sprite.setPosition(this.getX(),this.getY());
        sprite.setRotation((float)trajectory);
    }
}
