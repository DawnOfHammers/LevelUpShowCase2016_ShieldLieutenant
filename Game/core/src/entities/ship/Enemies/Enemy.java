package entities.ship.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import entities.ship.Ship;
import gamestates.playState.GameStage;

/**A generic Enemy superclass. All Enemy(s) are Ship(s).
 *
 * Created by Feng on 11/14/2015.
 */
public abstract class Enemy extends Ship {
    protected boolean[] actions;
    protected double goal_x, goal_y;
    protected double angle;
    protected int speed;
    protected int health;
    /**Creates a new enemy.
     *
     * This constructor should never be called in isolation.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    protected Enemy(int x, int y, String sprite_path, GameStage gs){
        super(x, y, gs);

        goal_x = x;
        goal_y = y;
	this.angle = Math.random()*Math.PI;

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
    public void act(float dt){
        aiPlan();
        aiAct();
        update();
        move();
    }


    /**Moves to a target point in a straight line.
     *
     */
    @Override
    protected void movePoint(){
        double dx = (goal_x - this.getX()), dy = (goal_y - this.getY());
        double hyp = Math.hypot(dx, dy);
        
        {//Angle set.
            this.angle = ;//TODO set angle based on dx and dy.
        }
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

    /**This move behavior is based on the angle of the ship, and not a point.
     *
     */
    @Override
    protected void moveAngle(){
        double dx = Math.sin(angle);
	double dy = Math.cos(angle);

        {// Ship Movement
            this.setX(this.getX() + (float) (speed * dx));
            this.setY(this.getY() + (float) (speed * dy));
        }
    }

    /**The Logic/AI of the enemy entities.ship.
     *      actions[0] = condition 0;
     *      actions[1] = condition 1;
     *      actions[2] = condition 2;
     *      ...
     */
    protected abstract void aiPlan();

    /**The implementation of individual enemy action.
     * Updates the enemy based on the boolean array actions.
     *      if(actions[0]){...}
     *      if(actions[1]){...}
     *      if(actions[2]){...}
     *      ...
     */
    protected abstract void aiAct();

    /**Gets the health of the Enemy entities.ship.
     *
     * @return  The current health of the entities.ship.
     */

    @Override
    public int getHealth() {
        return this.health;
    }

    /**Decreases the health of the Enemy entities.ship.
     *
     * If an enemy interacts with health and damage differently this method must be overridden.
     *
     * @param change    The change in health of the entities.ship.
     */

    @Override
    public void setHealth(int change) {
        this.health -= change;
    }

    protected abstract void update();

}
