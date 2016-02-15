package entities.shield;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import entities.GameObject;
import entities.projectiles.Bullet;
import gamestates.playState.GameStage;

/**
 * Created by Kevin on 12/31/2015.
 */
public class OmniShield extends Shield{
    ShapeRenderer shapeRenderer = new ShapeRenderer();

    public OmniShield(int x, int y, GameStage gs, int radius, String colour, String sprite_name){
        super(x, y, gs, radius, Math.PI*2, colour, sprite_name);

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

    public boolean check_in_arc(double x, double y){
        return true;
    }

    public boolean check_in_bounds(double x, double y){
        return true;
    }

}
