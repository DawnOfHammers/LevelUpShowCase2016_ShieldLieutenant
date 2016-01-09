package entities.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import entities.ship.player.Player;
import gamestates.playState.GameStage;


/**
 * Created by Hairuo on 2015-11-23.
 */
public class Missile extends Weapon {
    private double turn_speed;
    private ParticleEffect effect;


    public Missile(int x, int y, double trajectory, GameStage gs){
        super(x, y, trajectory, gs);
        this.speed = 3;
        this.sprite = new Sprite(new Texture(("bullet.jpg")));
        this.turn_speed = 0.75;

        effect = new ParticleEffect();
        effect.load(Gdx.files.internal("Trail_1.p"), Gdx.files.internal(""));
        effect.setPosition(this.getX() + sprite.getWidth()/2, this.getY());
        effect.start();

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        track(this.gamestage.getPlayer());
        update();
        effect.update(delta);
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




        //System.out.println(target_trajectory + "," + trajectory);
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
        effect.draw(batch);
    }

    /**
     * updates positions
     */
    protected void update() {

        track(gamestage.getPlayer());
        this.x_velo =  Math.sin(Math.toRadians(trajectory)) * speed;
        this.y_velo =  Math.cos(Math.toRadians(trajectory)) * speed;
        this.setX((float) (this.getX() + x_velo));
        this.setY((float) (this.getY() - y_velo));
        sprite.setPosition(this.getX(),this.getY());
        sprite.setRotation((float)trajectory);

        double[] t_coords = Laser.transform(this.getX() + sprite.getWidth()/2, this.getY() + sprite.getHeight(), 360 - this.trajectory , this.getX() + this.sprite.getWidth()/2, this.getY() + this.sprite.getHeight()/2);
        effect.setPosition((float)t_coords[0] , (float)t_coords[1]);
        com.badlogic.gdx.utils.Array<ParticleEmitter> emitters = effect.getEmitters();

        for( ParticleEmitter i : emitters){
            ParticleEmitter.ScaledNumericValue angle = i.getAngle();
            angle.setLow((float)this.trajectory+90);

        }

    }



}
