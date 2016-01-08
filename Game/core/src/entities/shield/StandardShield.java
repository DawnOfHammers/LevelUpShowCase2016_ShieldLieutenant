
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
    private ArrayList<Bullet> reflected;
    private ArrayList<ParticleEffect> effects;


    public StandardShield(double[] point, int radius) {
        super(point, radius, Math.PI);
        effects = new ArrayList<ParticleEffect>();
        reflected = new ArrayList<Bullet>();

    }


    /**
     * @param x: The x coordinate of the
     * @param y
     */

    @Override
    public void update(double x, double y, float delta) {
        point[0] = x;
        point[1] = y;

        for (ParticleEffect effect : effects) {
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
                if (check_in_arc(bullet)) {

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


                    effects.add(splash);


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
    private boolean check_in_arc(Bullet b) {
        double d_x = point[0] - b.getX();
        double d_y = point[1] - b.getY();
        double d_angle = -Math.atan2(d_y, d_x);

        if (d_angle < 0.0) {
            d_angle += Math.PI * 2;
            d_angle = Math.PI + Math.PI * 2 - d_angle;

        } else {
            d_angle = Math.PI - d_angle;
        }


        double end_angle = (arc_size + initial_angle) % (Math.PI * 2);
        if (initial_angle < d_angle && d_angle < end_angle) {
            return true;
        } else if (initial_angle > end_angle) {
            return (initial_angle > d_angle && d_angle < end_angle) || (initial_angle < d_angle && d_angle > end_angle);
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

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.arc((float) point[0], (float) point[1], radius, (float) Math.toDegrees(initial_angle) % 360, (float) Math.toDegrees(this.getFinalAngle()) % 360);
        shapeRenderer.end();

        batch.begin();

        for (ParticleEffect effect : effects) {
            effect.draw(batch);
        }

    }

    /**
     * Calculates the points and the rotations for the effects that compose the shield
     */
    public void calculatePoints(){

    }


}
