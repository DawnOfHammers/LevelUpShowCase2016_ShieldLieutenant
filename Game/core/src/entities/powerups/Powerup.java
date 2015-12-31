package entities.powerups;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.ship.player.Player;

/**
 * Created by Kevin on 2015-11-19.
 */
public abstract class Powerup extends Actor {
    /*
    */
    private int on_screen_timer;
    // The amount of time that the powerup will spend on the screen.

    private boolean active;
    //Is the powerup acting on the player?

    public Sprite sprite;
    //The image of the powerup.

    //public String name;
    //A string representation of the powerup.

    private int x;
    // X co-ordinate of the powerup.

    private int y;
    // Y co-ordinate of the powerup.

    public int timer;
    //The time that the powerup will be active on the player.

    public Powerup(int ost, int x, int y, String sprite_path, int timer){
        this.on_screen_timer = ost;
        this.active = false;
        this.x = x;
        this.y = y;
        this.timer = timer;

        {
            sprite = new Sprite(new Texture((sprite_path)));
            //setting origin based on size of sprite, setting bounds, etc

        }
    }

    public abstract void activate(Player player);
    //The actual function of the powerup. This needs to be implemented for each different powerup.

    public abstract void deactivate(Player player);
    //Deactivates the powerup on the player.

    public void update(){ // Entities.powerups only stay on screen for so long
        this.on_screen_timer -= 1;
    }

    public void draw(Batch batch, float parentAlpha) { //draws and moves the actor
        sprite.draw(batch);
    }

    public boolean get_active(){ return this.active; }//Return whether the powerup is onscreen or not


}
