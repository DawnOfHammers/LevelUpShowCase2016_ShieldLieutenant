package entities.ship.Enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import entities.shield.Shield;
import entities.shield.StandardShield;
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
    protected int speed;        //Subclass specific.
    protected int health;       //Subclass specific.
    protected int range;        //Subclass specific.
    /**Creates a new enemy.
     *
     * This constructor should never be called in isolation.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    protected Enemy(int x, int y, String sprite_name, GameStage gs){
        super(x, y, gs, sprite_name);
        init();

        goal_x = x;
        goal_y = y;
	    this.angle = Math.random()*Math.PI;

        this.sprite = new Sprite(new Texture(sprite_name+".png"));
        {// Sprite Setup
            sprite.setOrigin(sprite.getWidth()/2,sprite.getHeight()/2);
//            setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(),sprite.getHeight()); //initilization stuff for the actor
            setTouchable(Touchable.enabled);
        }
    }

    /**Initializes all required stats in an enemy.
     *
     */
    abstract protected void init();

    /**Enemies interact with the world in 3 ways.
     *
     *      - Thinking/Decision Making:
     *          -> Behavior of an enemy is determined by the state of the world.
     *             World -> Behavior
     *      -
     *
     */
    @Override
    public void act(float dt){
        super.act(dt);
        this.aiPlan();
        this.aiAct();

        for(double[] i : vertices) {
            for(Shield shield: gamestage.getPlayer().getShields()){
                if(shield.check_in_bounds(i[0], i[1])){
                    if(shield.check_in_arc(i[0], i[1])){
                        this.remove();
                    }
                }
            }
        }
    }


    /**Moves to a target point in a straight line.
     *
     */
    @Override
    protected void movePoint(){
        double dx = (goal_x - this.getX()), dy = (goal_y - this.getY());
        double hyp = Math.hypot(dx, dy);
        
        {//Angle set.
            this.angle = Math.toDegrees(Math.atan2(dy, dx)) + 90;//TODO set angle based on dx and dy.
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
            sprite.setRotation((float)- this.angle);
            sprite.setPosition(this.getX(), this.getY());
        }
    }

    /**This move behavior is based on the angle of the ship, and not a point.
     *
     */
    @Override
    protected void moveAngle(){
        double dx = Math.sin(Math.toRadians(angle));
	    double dy = Math.cos(Math.toRadians(angle));

        {// Ship Movement
            this.setX(this.getX() + (float) (speed * dx));
            this.setY(this.getY() + (float) (speed * dy));
        }
        {// Sprite Movement
            sprite.setRotation((float)- this.angle);
            sprite.setPosition(this.getX(), this.getY());
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
}
