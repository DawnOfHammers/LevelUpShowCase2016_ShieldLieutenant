package entities.shield;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;

import entities.projectiles.Bullet;

import java.util.ArrayList;

/**
 * This class will be the superclass of the primary entities.shield of the character.
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public abstract class Shield extends Actor {
    /**
     * The location of the entities.ship. This is the point at which the entities.shield is centred.
     */
    protected double[] point;

    /**
     * The integer distance from the entities.ship to the centre of the entities.shield.
     */
    protected int radius;

    /**
     * The angle in RADIANS of the arc size of the entities.shield.
     */
    protected double arc_size;

    /**
     * The initial angle of the entities.shield. This will automatically be initiated to be 0.
     */
    protected double initial_angle = 0;

    /**
     * This is the angular velocity of the entities.shield.
     */
    private final double PRESS_SPEED = Math.PI / 36;

    /**
     * String representation of the colour of the shield
     */
    protected String colour;

    /**
     * list of bullets that have passed through the radius
     */
    protected ArrayList<Bullet> reflected;

    /**
     * list of effects that need to be attached
     */
    protected ArrayList<ParticleEffect> reflect_effects;
    protected ArrayList<ParticleEffect> shield_effects;


    /**
     * The primary constructor of entities.shield.
     *
     * @param p:        Point
     * @param radius:   int
     * @param arc_size: double
     */
    public Shield(double[] p, int radius, double arc_size, String colour) {
        this.point = p;
        this.radius = radius;
        this.arc_size = arc_size;
        this.colour = colour;
    }

    /**
     * This method will rotate the entities.shield clockwise
     */
    public void rotateClockwise() {
        this.initial_angle += PRESS_SPEED;
        while (initial_angle > Math.PI * 2) {
            initial_angle -= Math.PI * 2;
        }
    }

    /**
     * This method will rotate the entities.shield counterclockwise
     */
    public void rotateCounterClockwise() {
        this.initial_angle -= PRESS_SPEED;
        while (initial_angle < 0) {
            initial_angle += Math.PI * 2;
        }
    }

    /**
     * This is the collision method between any given bullet and a entities.shield
     *
     * @param b: This is the bullet at which collides
     * @return boolean: This returns whether or not the bullet did collide with the entities.shield.
     */
    public abstract boolean collideProjectile(Bullet b);


    /**
     * This is the collision method between any given laser and a shield
     *
     * @param x: x coordinate to be checked
     * @param y: y coordinate to be checked
     * @param trajectory: current angle of the bullet.
     * @return: Whether the bullet has collided or not.
     */
    public abstract double collideLaser(double x, double y, double trajectory);

    /**
     * Returns the ending angle of the arc that represents the entities.shield.
     *
     * @return double: The ending angle of the arc
     */
    public double getFinalAngle() {
        return arc_size;
    }

    /**
     * Updates all logic of the Shield
     */
    public abstract void update(double x, double y, float delta);
}