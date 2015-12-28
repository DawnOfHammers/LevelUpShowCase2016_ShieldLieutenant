package entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import gamestates.playState.GameStage;


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
    protected GameStage gamestage;

    public Weapon(int x, int y, double trajectory, GameStage gs){
        //System.out.println(x+","+y);
        //System.out.println(y);
        this.setX(x);
        this.setY(y);
        this.trajectory = trajectory;
        this.gamestage = gs;
    }

    protected abstract void update();








}





