package ship;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.GameStage;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-05.
 */
public abstract class Ship extends Actor
{
    protected Sprite sprite;
    protected int health;

    public Ship(int x, int y){
        this.setX(x);
        this.setY(y);
    }


    protected abstract void move();
    public abstract void update(GameStage game_stage);
    public abstract int getHealth();
    public abstract void setHealth(int change);
    public void draw(Batch batch, float parentAlpha) { //draws and moves the actor
        sprite.draw(batch);
    }
}
