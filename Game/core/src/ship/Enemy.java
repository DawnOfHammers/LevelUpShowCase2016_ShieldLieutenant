package ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import java.util.ArrayList;

/**A generic Enemy superclass. All Enemy(s) are Ship(s).
 *
 * Created by Feng on 11/14/2015.
 */
public abstract class Enemy extends Ship {
    protected boolean[] actions;
    protected double goal_x, goal_y;
    /**Creates a new enemy.
     *
     * This constructor should never be called in isolation.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    protected Enemy(int x, int y, String sprite_path){
        super(x, y);
        goal_x = x;
        goal_y = y;

        {// Sprite Setup
            super.sprite = new Sprite(new Texture((sprite_path)));
            sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
            setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(),sprite.getHeight()); //initilization stuff for the actor
            setTouchable(Touchable.enabled);
        }
    }

    /**Enemies interact with the world in 3 ways.
     *
     *      - Thinking/Decision Making:
     *          -> Behavior of an enemy is determined by the state of the world.
     *             World -> Behavior
     *      -
     *
     */
    @Override
    public void update(ArrayList<Actor> actors) {
        System.out.print(health);
        aiPlan(actors);
        aiAct(actors);

        move();
    }

    /**Moves to a target location in a straight line.
     *
     * For ships with specialized movement this method will be overridden.
     */
    @Override
    protected void move() {
        int speed = 2;
        double dx = (goal_x - this.getX()), dy = (goal_y - this.getY());
        double hyp = Math.hypot(dx, dy);

        {// Ship Movement
            if(hyp > speed) {
                this.setX(this.getX() + (float) (speed * dx / hyp));
                this.setY(this.getY() + (float) (speed * dy / hyp));
            }
            else{
                this.setX((float)goal_x);
                this.setY((float)goal_y);
            }
        }
        {// Sprite Movement
            sprite.setRotation(this.getRotation());
            sprite.setPosition(this.getX(), this.getY());
        }
    }

    /**The Logic/AI of the enemy ship.
     *      actions[0] = condition 0;
     *      actions[1] = condition 1;
     *      actions[2] = condition 2;
     *      ...
     */
    protected abstract void aiPlan(ArrayList<Actor> actors);
    /**The implementation of individual enemy action.
     * Updates the enemy based on the boolean array actions.
     *      if(actions[0]){...}
     *      if(actions[1]){...}
     *      if(actions[2]){...}
     *      ...
     */
    protected abstract void aiAct(ArrayList<Actor> actors);

    /**Gets the health of the Enemy ship.
     *
     * @return  The current health of the ship.
     */
    @Override
    public int getHealth() {
        return this.health;
    }

    /**Decreases the health of the Enemy ship.
     *
     * If an enemy interacts with health and damage differently this method must be overridden.
     *
     * @param change    The change in health of the ship.
     */
    @Override
    public void setHealth(int change) {
        this.health -= change;
    }
}
