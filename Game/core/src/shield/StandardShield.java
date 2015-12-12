package shield;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import projectiles.Bullet;
import java.awt.Point;

/**
 * This class is the StandardShield used by the character. It has an overridden collide method
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public class StandardShield extends Shield{
    ShapeRenderer shapeRenderer = new ShapeRenderer();


    public StandardShield(double [] point, int radius){
        super(point, radius, Math.PI/3);

    }


    /**
     * @param x: The x coordinate of the
     * @param y
     */
    @Override
    public void update(double x, double y) {
        point[0] = x;
        point[1] = y;

    }

    /**
     * The overriden collide method in the shield class
     * @param bullet: the bullet that you need to check collision with.
     */
    public boolean collideProjectile(Bullet bullet) {
        if(!bullet.isBounced()) {
            double delta_x = point[0] - bullet.getX();
            double delta_y = point[1] - bullet.getY();
            double distance_from_player = Math.hypot(delta_x, delta_y);
            if (distance_from_player < radius) {
                double ref_trajectory = Math.atan2(delta_y / distance_from_player, delta_x / distance_from_player);
                if (arc_size - ref_trajectory + initial_angle > 0) {
                    //System.out.println(bullet.getTrajectory() + "a             " + Math.toDegrees(ref_trajectory));
                    double end_trajectory = (2*Math.toDegrees(ref_trajectory) - bullet.getTrajectory());

                    bullet.setTrajectory(-180 - end_trajectory);
                    //System.out.println(bullet.getTrajectory() + "b");
                    //bullet.setX((float)Math.cos(Math.toDegrees(ref_trajectory)) * radius);
                    //bullet.setY((float)Math.sin(Math.toDegrees(ref_trajectory)) * radius);
                    return true;
                }
            }
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
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.arc((float) point[0], (float) point[1], radius, (float)Math.toDegrees(initial_angle)%360, (float) Math.toDegrees(this.getFinalAngle())%360);
        shapeRenderer.end();  
        batch.begin();

    }
}
