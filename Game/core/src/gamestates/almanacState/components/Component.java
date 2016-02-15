package gamestates.almanacState.components;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Hongyu Wang on 2/15/2016.
 */
public abstract class Component {
    protected int x_loc, y_loc;
    protected Sprite sprite;




    public Component(int x, int y, Sprite sprite){
        init();
        this.x_loc = x;
        this.y_loc = y;
        this.sprite = sprite;
    }




    protected abstract void init();

}
