package gamestates.almanacState.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Hongyu Wang on 2/15/2016.
 * This is the general component superclass.
 * This is primarily used within the menu and almanac classes.
 * This is for easier menu layout.
 */
public abstract class Component {
    protected int x_loc, y_loc, width, height;
    protected Sprite sprite;
    protected boolean visible;

    /**
     * @param x The x-coordinate of the component.
     * @param y The y-coordinate of the component.
     * @param width The width of the component.
     * @param height The height of the component.
     * @param sprite The optional sprite of the component.
     *
     */
    public Component(int x, int y, int width, int height, Sprite sprite){
        init();
        this.x_loc = x;
        this.y_loc = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    //This is the central init function.
    //This will be called each time a new Component will be created.
    protected abstract void init();

    /**
     * The draw function of the component:
     * This will all assets within the component.
     */
    protected abstract void draw(SpriteBatch sb);

    /**
     * This is the logic on the component iff the component is touched/pressed.
     */
    protected abstract void doPressLogic();

    /**
     * This method will check this component is pressed.
     * Following this, it will perform any action required
     * @param x the x-coordinate of the press/touch
     * @param y the y-coordinate of the press/touch
     */
    public void checkPressed(int x, int y){
        if (checkInX(x) && checkInY(y)){
            doPressLogic();
        }
    }

    /**
     * This method determines the visibility of the component.
     * i.e. whether or not the component will display.
     * When this method is called, it will switch the visibility to the opposite of what it was before.
     */
    public void setVisible(){
        visible = !visible;
    }

    //This function checks whether or not the x coordinate is within the width and height bounds of the object
    private boolean checkInX(int x){
        return width > x - x_loc && x - x_loc > 0;
    }

    //This function checks whether or not the y coordinate is within the width and height bounds of the object.
    private boolean checkInY(int y){
        return height > y - y_loc && y - y_loc > 0;
    }

}
