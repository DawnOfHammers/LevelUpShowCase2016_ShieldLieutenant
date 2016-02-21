package entities.shield;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import gamestates.playState.GameStage;

/**
 * Created by Kevin on 2/20/2016.
 */
public class OrbitShield extends StandardShield {

    public int TIMER;

    public OrbitShield(int x, int y, GameStage gs, int radius, String colour, String sprite_name){
        super(x, y, gs, radius, colour, sprite_name, Math.PI/6);

    }

    @Override
    public void update(double x, double y, float delta) {

        rotateClockwise();

        super.update(x,y,delta);
    }
}
