package projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Hairuo on 2015-11-18.
 */
public abstract class Weapon extends Actor {
    Sprite sprite_sheet;
    Sprite sprite;
    protected double trajectory;
    protected double x_velo;
    protected double y_velo;
    protected int speed;

    public Weapon(int x, int y, double trajectory){
        //System.out.println(x+","+y);
        //System.out.println(y);
        this.setX(x);
        this.setY(y);

    }

    abstract void update();





}





