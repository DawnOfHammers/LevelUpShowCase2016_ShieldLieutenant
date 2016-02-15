package entities.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.GameObject;
import entities.ship.player.Player;
import gamestates.playState.GameStage;


/**
 * Created by Hairuo on 2015-11-18.
 */
public abstract class Weapon extends GameObject {
    protected double trajectory;
    protected double x_velo;
    protected double y_velo;
    protected int speed;

    /**
     * Base constructor for all weapons
     * Assigns basic values
     * @param x starting position of weapon
     * @param y starting position of weapon
     * @param trajectory starting angle of weapon
     * @param gs game stage
     */
    public Weapon(int x, int y, double trajectory, GameStage gs, String sprite_name) {
        super(x, y, gs, sprite_name);
        //System.out.println(x+","+y);
        //System.out.println(y);
        this.trajectory = trajectory;
    }

    /**
     * update method that must be implemented in all weapon types
     * Checks if the weapon has hit the player removes it if it does
     */
    protected void update() {

        Player i = gamestage.getPlayer();

        double delta_x = this.getX() - i.getX();
        double delta_y = this.getY() - i.getY();
        double distance_from_player = Math.hypot(delta_x, delta_y);

        if (distance_from_player < 50) {
            //System.out.println(health);
            i.setHealth(-1);
            this.remove();

        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    /**
     * test
     * @return ???
     */

    @Override
    public boolean remove() {
        gamestage.getWeapons().remove(this);
        return super.remove();
    }
}





