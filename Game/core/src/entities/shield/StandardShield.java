
package entities.shield;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import entities.projectiles.Bullet;
import entities.projectiles.Laser;
import gamestates.playState.GameStage;

import java.util.ArrayList;


/**
 * This class is the StandardShield used by the character. It has an overridden collide method
 *
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public class StandardShield extends Shield {
    /**
     * shape renderer for primilary shield drawing
     * now defunct
     */
    private ShapeRenderer shapeRenderer = new ShapeRenderer();


    public StandardShield(int x, int y, GameStage gs, int radius, String colour, String sprite_name) {
        super(x, y, gs, radius, Math.PI / 2, colour, sprite_name);
        reflect_effects = new ArrayList<ParticleEffect>();
        shield_effects = new ArrayList<ParticleEffect>();
        reflected = new ArrayList<Bullet>();
        createShieldEffects();

    }

    public StandardShield(int x, int y, GameStage gs, int radius, String colour, String sprite_name, double arc_size){
        super(x, y, gs, radius, arc_size, colour, sprite_name);
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
        this.setX((int)x);
        this.setY((int) y);

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

            for(double[] i : bullet.getVertices() ){

                if(check_in_bounds(i[0], i[1])){
                    reflected.add(bullet);
                    if (check_in_arc(i[0], i[1])) {

                        double slope_of_tangent = -((this.getX() - i[0]) / (this.getY() - i[1]));
                        float reflected_angle = (float) Math.toDegrees(-((2 * Math.atan(slope_of_tangent) - (Math.toRadians(-bullet.getTrajectory()) + Math.PI / 2)) - Math.PI / 2));
                        bullet.setTrajectory(reflected_angle);

                        ParticleEffect splash = new ParticleEffect();
                        splash.load(Gdx.files.internal("splash_2.p"), Gdx.files.internal(""));
                        splash.setPosition((float)i[0], (float)i[1]);
                        splash.start();

                        com.badlogic.gdx.utils.Array<ParticleEmitter> emitters = splash.getEmitters();
                        for (ParticleEmitter emitter : emitters) {
                            ParticleEmitter.ScaledNumericValue angle = emitter.getAngle();
                            angle.setLow(360 - reflected_angle + 90 - 27);
                            angle.setHigh(360 - reflected_angle + 90 - 27, 360 - reflected_angle + 90 - 27 + 70);

                        }


                        reflect_effects.add(splash);


                        return true;
                    }
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
        if(check_in_bounds(x, y)){


            if (check_in_arc(x, y)) {
                double slope_of_tangent = -((this.getX() - x) / (this.getY() - y));
                return (Math.toDegrees(-((2 * Math.atan(slope_of_tangent) - (Math.toRadians(-trajectory) + Math.PI / 2)) - Math.PI / 2)));
            }
        }
        return 10000;
    }

    /**
     * Checks whether or not the missile has hit the shield
     *
     * @param x:        x coordinate to be checked
     * @param y:        y coordinate to be checked
     * @return whether or not the missile has hit an arc
     */
    public boolean collideMissile(double x, double y){
        if(check_in_bounds(x, y)){
            if (check_in_arc(x, y)) {

                ParticleEffect splash = new ParticleEffect();
                splash.load(Gdx.files.internal("explosion.p"), Gdx.files.internal(""));
                splash.setPosition((float)x, (float)y);
                splash.start();
                reflect_effects.add(splash);

                return true;
            }
        }
        return false;
    }

    //TODO Implement this function
    /**
     *Checks whether or not the x and y coordinate is within the arc of the shield
     *
     * @param x x value of point to be checked
     * @param y y value of point to be checked
     * @return boolean, whether its in arc or not
     */
    public boolean check_in_arc(double x, double y) {


        double d_x = this.getX() - x;
        double d_y = this.getY() - y;
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

    public boolean check_in_bounds(double x, double y){
        double delta_x = this.getX() - x;
        double delta_y = this.getY() - y;
        double distance_from_player = Math.hypot(delta_x, delta_y);
        if(distance_from_player < radius + 17){
            return true;
        }
        return false;

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
        shapeRenderer.arc((float) this.getX(), (float) this.getY(), radius, (float) Math.toDegrees(initial_angle) % 360, (float) Math.toDegrees(this.getFinalAngle()) % 360);
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
        double x = radius * Math.cos(s_angle) + this.getX();
        double y = radius * Math.sin(s_angle) + this.getY();

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

            x = radius * Math.cos(s_angle) + this.getX();
            y = radius * Math.sin(s_angle) + this.getY();


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

            double x = radius * Math.cos(s_angle) + this.getX();
            double y = radius * Math.sin(s_angle) + this.getY();
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