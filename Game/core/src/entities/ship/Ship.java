package entities.ship;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.GameObject;
import game.Tools.RGBsplitter;
import game.Tools.Shift;
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-05.
 */
public abstract class Ship extends GameObject {
    /**
     *
     *
     */
    protected int health;
    protected GameStage gamestage;
    protected RGBsplitter split;

    /**
     * Creates a new ship
     *
     * Should be called in all subclasses
     *
     * Assigns basic values to attributes
     *
     * Creates the R,G,B channels of the sprite for the distortion effect
     *
     * @param x x coordinate of the object
     * @param y y coordinate of the object
     * @param gs gamestage that the object is contained in
     * @param sprite_name file name without the extension of the sprite
     */
    public Ship(int x, int y, GameStage gs, String sprite_name){
        super(x,y,gs,sprite_name);
        this.setX(x);
        this.setY(y);
        this.gamestage = gs;
        this.sprite = new Sprite(new Texture(sprite_name+".png"));



    }

    protected abstract void moveAngle();
    protected abstract void movePoint();
    public abstract int getHealth();
    public abstract void setHealth(int change);


    /**
     * Overridden act method of the actor class
     *
     * Updates the distortion effect if it is active
     * @param delta
     */
    @Override
    public void act(float delta) {
        super.act(delta);

    }

    /**
     * Overridden draw method of the actor class
     *
     * Draws either the sprite or the distortion effect if it is active
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) { //draws and moves the actor
        super.draw(batch, parentAlpha);


    }



}
