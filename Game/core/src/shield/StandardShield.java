package shield;
import projectiles.Bullet;
import java.awt.Point;

/**
 * This class is the StandardShield used by the character. It has an overridden collide method
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public class StandardShield extends Shield{
    public StandardShield(double [] point, int radius){
        super(point, radius, Math.PI/3);
    }

    /**
     * The overriden collide method in the shield class
     * @param bullet: the bullet that you need to check collision with.
     */
    public boolean collideProjectile(Bullet bullet) {
        double delta_x = point[0] - bullet.getX();
        double delta_y = point[1] - bullet.getY();
        double distance_from_player = Math.hypot(delta_x, delta_y);
        if (distance_from_player < radius){
            double ref_trajectory = Math.atan2(delta_y/distance_from_player, delta_x/distance_from_player);
            if (arc_size - ref_trajectory + initial_angle > 0){
                bullet.setTrajectory(ref_trajectory + bullet.getTrajectory());
                bullet.getLocation().setLocation(Math.cos(ref_trajectory)*radius, Math.sin(ref_trajectory)*radius);
                return true;
            }
        }
        return false;
    }

}
