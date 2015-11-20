package powerups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by KMFK on 2015-11-19.
 */
public abstract class Powerup extends Actor{
    // I just needed to make a file to look like I did something
    /*
    */
    private int on_screen_timer;
    // The amount of time that the powerup will spend on the screen.

    private int active_timer;
    // The amount of time that the powerup will be active on the player
    // Optionally, this could be set to a static amount within the player ship, but it's good for different
    // powerups to have different timers.

    private boolean active;
    //Is the powerup active on the screen?

    private Sprite sprite;
    //The image of the powerup.

    private int x;
    // X co-ordinate of the powerup.

    private int y;
    // Y co-ordinate of the powerup.

    public Powerup(int ost,int at,int x, int y, String sprite_path){
        this.on_screen_timer = ost;
        this.active_timer = at;
        this.active = true;
        this.x = x;
        this.y = y;

        {
            sprite = new Sprite(new Texture((sprite_path)));
            //setting origin based on size of sprite, setting bounds, etc

        }
    }

    public abstract void activate();
    //The actual function of the powerup. This needs to be implemented for each different powerup.

    public void update(){ // powerups only stay on screen for so long
        this.on_screen_timer -= 1;
        if (this.on_screen_timer == 0){
            this.active = false;
        }
    }

    public void draw(Batch batch, float parentAlpha) { //draws and moves the actor
        sprite.draw(batch);
    }

    public boolean get_active(){ return this.active; }//Return whether the powerup is onscreen or not


}
