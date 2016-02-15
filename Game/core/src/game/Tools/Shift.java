package game.Tools;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import gamestates.playState.GameStage;

/**
 * Created by Hairuo on 2016-01-21.
 */
public class Shift extends Actor{
    private double x_curr_shift;
    private double y_curr_shift;
    private int type;
    private int duration;
    private int run_time;
    private Actor base;
    private int x_shift;
    private Sprite sprite;
    private GameStage gamestage;
    private int y_shift;
    private int multiplier;


    public Shift(int type, GameStage gamestage, String sprite_name, Actor base){
        this.x_curr_shift = 0;
        this.y_curr_shift = 0;
        this.type = type;
        this.x_shift = 0;
        this.y_shift = 0;
        this.multiplier = 1;
        this.run_time =0;
        this.gamestage = gamestage;
        this.sprite = new Sprite(new Texture(sprite_name+".png"));
        this.base = base;
        if(type == 0){
            this.sprite = new Sprite(new Texture(sprite_name+"_green.png"));
        }else if(type == 1){
            this.sprite = new Sprite(new Texture(sprite_name+"_red.png"));
        }else if(type == 2){
            this.sprite = new Sprite(new Texture(sprite_name+"_blue.png"));
        }

        generateShift();


    }

    /**
     * Overridden draw method of the actor class
     *
     * Draws the sprite
     *
     * @param batch spritebatch of the stage
     * @param parentAlpha transparency
     */

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);

    }


    /**
     * Overridden act method of the actor class
     *
     * calls the update method
     *
     * @param delta time in milliseconds between calls
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        update(base.getX(), base.getY(), base.getRotation());
    }

    /**
     * Updates the current x and y shift
     *
     * Updates the actors x and y position
     *
     * Updates the sprites x and y postition, and rotation
     *
     * Increments the run_time and checks if it has hit the target duration
     * Once it has it calls the generateShift method
     */
    public void update(float x, float y, float rotation){
        x_curr_shift += x_shift/2;
        y_curr_shift += y_shift/2;
        this.setX(x + (float)x_curr_shift);
        this.setY(y + (float)y_curr_shift);
        sprite.setPosition(this.getX(), this.getY());
        sprite.setRotation(rotation);
        run_time++;

        if(run_time >= duration){
            generateShift();

        }
    }

    /**
     *  Generates the x and y shift per frame for the sprite
     *
     *  Generates the duration of the shift
     *
     *  Reverts the current shift to 0, 0
     */
    public void generateShift(){

        x_shift = (int)(Math.random()*3*multiplier) + 1;
        y_shift = (int)(Math.random()*3*multiplier) + 1;
        duration = (int)(Math.random()*10*multiplier) + 1;
        x_curr_shift = 0;
        y_curr_shift = 0;
        run_time = 0;



    }
}
