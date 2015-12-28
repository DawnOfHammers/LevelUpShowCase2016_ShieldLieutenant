package entities.shield;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import entities.projectiles.Bullet;

/**
 * This class is the StandardShield used by the character. It has an overridden collide method
 * @author Hongyu Wang
 * @version 1.0
 * @since 2015/11/14
 */
public class StandardShield extends Shield{
    ShapeRenderer shapeRenderer = new ShapeRenderer();


    public StandardShield(double [] point, int radius){
        super(point, radius, Math.PI*2-0.1);

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
     * The overriden collide method in the entities.shield class
     * This returns a boolean because if it collided, then it should be TRUE!
     * @param bullet: the bullet that you need to check collision with.
     */
    public boolean collideProjectile(Bullet bullet) {
        if(!bullet.isBounced()) {
            double delta_x = point[0] - bullet.getX();
            double delta_y = point[1] - bullet.getY();
            double distance_from_player = Math.hypot(delta_x, delta_y);
            if (distance_from_player < radius) {
                if (check_in_arc(bullet)) {
                    double slope_of_tangent = -(delta_x / delta_y);
                    bullet.setTrajectory(Math.toDegrees(-((2*Math.atan(slope_of_tangent) - (Math.toRadians(-bullet.getTrajectory()) + Math.PI/2)) -Math.PI/2)));
                    return true;
                }
            }
        }
        return false;
    }


    //TODO Implement this function
    private boolean check_in_arc(Bullet b){
        return !(b.getX() == 600000);

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
