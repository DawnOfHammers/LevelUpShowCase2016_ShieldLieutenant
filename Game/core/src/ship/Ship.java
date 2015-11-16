package ship;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Hairuo on 2015-11-05.
 */
public abstract class Ship extends Actor
{
    protected Sprite sprite;
    protected double coordX;
    protected double coordY;
    protected int health;

    public Ship(int x, int y){
        this.coordX = x;
        this.coordY = y;

    }


    protected abstract void move();
    public abstract void update();







}
