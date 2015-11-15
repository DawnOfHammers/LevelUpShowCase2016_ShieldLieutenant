package projectiles;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.*;

/**
 * This class will be the superclass of the bullet class.
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public abstract class Bullet extends Actor {
    /**
     * The current location of the bullet.
     */
    protected Point location;

    /**
     * The current trajectory of the bullet. This is an angle in radians.
     */
    protected double trajectory;


    /**
     * Gets the location as a <Point></Point>
     * @return Point
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Gets the trajectory of the bullet as a radian angle.
     * @return trajectory
     */
    public double getTrajectory() {
        return trajectory;
    }

    /**
     * Sets the trajectory of the bullet as a radian angle.
     * @param trajectory
     */
    public void setTrajectory(double trajectory) {
        this.trajectory = trajectory;
    }
}
