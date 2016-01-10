
package entities.shield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import entities.projectiles.Bullet;

import java.util.ArrayList;


/**
 * This class is the StandardShield used by the character. It has an overridden collide method
 *
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public class StandardShield extends Shield {
    private ShapeRenderer shapeRenderer = new ShapeRenderer();





    public StandardShield(double[] point, int radius, String colour) {
        super(point, radius, Math.PI, colour);
        reflect_effects = new ArrayList<ParticleEffect>();
        shield_effects = new ArrayList<ParticleEffect>();
        reflected = new ArrayList<Bullet>();
        createShieldEffects();

    }


    /**
     * @param x: The x coordinate of the
     * @param y
     */

    @Override
    public void update(double x, double y, float delta) {
        point[0] = x;
        point[1] = y;

        updateShieldEffects();

        for (ParticleEffect effect : reflect_effects) {
            effect.update(delta);
        }
        for (ParticleEffect effect : shield_effects) {
            effect.update(delta);
        }




    }

    /**
     * The overriden collide method in the entities.shield class
     * This returns a boolean because if it collided, then it should be TRUE!
     *
     * @param bullet: the bullet that you need to check collision with.
     */
    public boolean collideProjectile(Bullet bullet) {
        if(!reflected.contains(bullet)) {
            double delta_x = point[0] - bullet.getX();
            double delta_y = point[1] - bullet.getY();
            double distance_from_player = Math.hypot(delta_x, delta_y);
            if (distance_from_player < radius) {
                reflected.add(bullet);
                if (check_in_arc(bullet.getX(), bullet.getY())) {

                    double slope_of_tangent = -(delta_x / delta_y);
                    float reflected_angle = (float) Math.toDegrees(-((2 * Math.atan(slope_of_tangent) - (Math.toRadians(-bullet.getTrajectory()) + Math.PI / 2)) - Math.PI / 2));
                    bullet.setTrajectory(reflected_angle);

                    ParticleEffect splash = new ParticleEffect();
                    splash.load(Gdx.files.internal("splash_2.p"), Gdx.files.internal(""));
                    splash.setPosition(bullet.getX(), bullet.getY());
                    splash.start();

                    com.badlogic.gdx.utils.Array<ParticleEmitter> emitters = splash.getEmitters();
                    for (ParticleEmitter i : emitters) {
                        ParticleEmitter.ScaledNumericValue angle = i.getAngle();
                        angle.setLow(360 - reflected_angle + 90 - 27);
                        angle.setHigh(360 - reflected_angle + 90 - 27, 360 - reflected_angle + 90 - 27 + 55);

                    }


                    reflect_effects.add(splash);


                    return true;
                }
            }
        }
        return false;
    }

    /**
     * returns the resulting angle based on the parameters given
     * if it doesnt collide returns an arbitrairily high number
     *
     * @param x:          x coordinate to be checked
     * @param y:          y coordinate to be checked
     * @param trajectory: current angle of the laser
     */
    public double collideLaser(double x, double y, double trajectory) {
        double delta_x = point[0] - x;
        double delta_y = point[1] - y;
        double distance_from_player = Math.hypot(delta_x, delta_y);

        if (distance_from_player < radius) {


            if (true) {
                double slope_of_tangent = -(delta_x / delta_y);
                return (Math.toDegrees(-((2 * Math.atan(slope_of_tangent) - (Math.toRadians(-trajectory) + Math.PI / 2)) - Math.PI / 2)));
            }
        }
        return 10000;
    }


    //TODO Implement this function

    /**
     *
     * @param x x value of point to be checked
     * @param y y value of point to be checked
     * @return boolean, whether its in arc or not
     */
    private boolean check_in_arc(double x, double y) {
        double d_x = point[0] - x;
        double d_y = point[1] - y;
        double d_angle = -Math.atan2(d_y, d_x);

        if (d_angle < 0.0) {
            d_angle += Math.PI * 2;
            d_angle = Math.PI + Math.PI * 2 - d_angle;

        } else {
            d_angle = Math.PI - d_angle;
        }


        double end_angle = (arc_size + initial_angle) % (Math.PI * 2);


        if (initial_angle <= d_angle && d_angle <= end_angle) {
            return true;
        } else if (initial_angle >= end_angle) {
            return (initial_angle >= d_angle && d_angle <= end_angle) || (initial_angle <= d_angle && d_angle >= end_angle);
        } else {
            return false;
        }


    }


    /**
     * The overriden draw method in the actor class
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {

        //The following is just test code to see the actual arc
        batch.end();

        /**
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.arc((float) point[0], (float) point[1], radius, (float) Math.toDegrees(initial_angle) % 360, (float) Math.toDegrees(this.getFinalAngle()) % 360);
        shapeRenderer.end();
        **/
        batch.begin();

        for (ParticleEffect effect : reflect_effects) {
            effect.draw(batch);
        }

        for (ParticleEffect effect : shield_effects) {
            effect.draw(batch);
        }

    }

    /**
     * Calculates the points and the rotations for the effects that compose the shield
     */
    public void createShieldEffects(){
        double s_angle = initial_angle+ 0.001;
        double x = radius * Math.cos(s_angle) + point[0];
        double y = radius * Math.sin(s_angle) + point[1];

        ParticleEffect burn_1 = new ParticleEffect();
        burn_1.setPosition((float) x, (float) y);
        burn_1.start();


        shield_effects.add(burn_1);

        while(check_in_arc(x, y)) {
            if (!check_in_arc(x, y)){
                break;
            }
            s_angle = s_angle % (Math.PI*2);
            s_angle += Math.PI/48;

            if(s_angle < 0){
                s_angle += Math.PI*2;
            }

            x = radius * Math.cos(s_angle) + point[0];
            y = radius * Math.sin(s_angle) + point[1];


            ParticleEffect burn_2 = new ParticleEffect();
            burn_2.load(Gdx.files.internal("Burn_s.p"), Gdx.files.internal(""));
            burn_2.setPosition((float)x, (float)y);
            burn_2.start();

            shield_effects.add(burn_2);
        }
    }

    public void updateShieldEffects(){
        double s_angle = initial_angle;
        for(ParticleEffect effect : shield_effects){

            double x = radius * Math.cos(s_angle) + point[0];
            double y = radius * Math.sin(s_angle) + point[1];
            com.badlogic.gdx.utils.Array<ParticleEmitter> emitters = effect.getEmitters();
            for (ParticleEmitter i : emitters) {



                if(s_angle < 0){
                    s_angle += Math.PI*2;
                }

                i.setPosition((float) x, (float) y);
                ParticleEmitter.ScaledNumericValue angle = i.getAngle();
                angle.setLow((float)Math.toDegrees(s_angle-90));
                angle.setHigh((float) Math.toDegrees(s_angle - 90), (float) Math.toDegrees(s_angle + 90));

                ParticleEmitter.ScaledNumericValue velo = i.getVelocity();


                if(colour.equals("orange")){
                    ParticleEmitter.GradientColorValue c_colour = i.getTint();
                    c_colour.setColors(new float[]{1, 0.22352941f, 0.047058824f});

                }

            }
            s_angle = s_angle % (Math.PI*2);
            s_angle += Math.PI/48;
        }

    }


}
