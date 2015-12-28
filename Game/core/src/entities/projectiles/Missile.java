package entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.GameStage;

/**
 * Created by Hairuo on 2015-11-23.
 */
public class Missile extends Weapon {

    public Missile(int x, int y, double trajectory){
        super(x, y, trajectory);
    }
    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
<<<<<<< HEAD:Game/core/src/entities/projectiles/Missile.java
    protected void update() {
=======
    void update(GameStage game_stage) {
>>>>>>> origin/master:Game/core/src/projectiles/Missile.java

    }
}
