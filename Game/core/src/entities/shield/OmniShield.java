package entities.shield;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import entities.projectiles.Bullet;

/**
 * Created by Kevin on 12/31/2015.
 */
public class OmniShield extends Shield{
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public OmniShield(double[] point, int radius, String colour){
        super(point, radius, Math.PI*2, colour);

    }
    //TODO Gotta implement these two based off of Standard Shield, will tackle later
    public boolean collideProjectile(Bullet b){
        return true;
    }

    public double collideLaser(double x, double y, double trajectory){
        return 10.0;
    }

    public boolean collideMissile(double x, double y){
        return true;
    }

    public void update(double x, double y, float delta){
        point[0] = x;
        point[1] = y;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        //The following is just test code to see the actual arc
        batch.end();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.circle((float) point[0], (float) point[1], radius);
        shapeRenderer.end();
        batch.begin();

    }

}
