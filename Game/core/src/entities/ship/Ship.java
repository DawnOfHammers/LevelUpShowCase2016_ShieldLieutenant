package entities.ship;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import gamestates.playState.GameStage;

/**
 * Created by Hairuo on 2015-11-05.
 */
public abstract class Ship extends Actor
{
    protected Sprite sprite;
    protected int health;
    protected GameStage gamestage;
    public Ship(int x, int y, GameStage gs){
        this.setX(x);
        this.setY(y);
        this.gamestage = gs;
    }


    protected abstract void move();
    public abstract int getHealth();
    public abstract void setHealth(int change);
    public void draw(Batch batch, float parentAlpha) { //draws and moves the actor
        sprite.draw(batch);
    }


}
