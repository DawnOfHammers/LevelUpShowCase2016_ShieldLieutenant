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
public class OmniShield extends StandardShield{

    public OmniShield(int x, int y, GameStage gs, int radius, String colour, String sprite_name) {
        super(x, y, gs, radius, colour, sprite_name, Math.PI*2-(1/(Math.PI*2)));

    }

}
