package ship;

import ship.Ship;

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
    protected Enemy(int x, int y){
        super(x, y);
        goal_x = x;
        goal_y = y;
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
    public void update() {
        aiPlan();
        aiAct();

        move();
    }

    /**The Logic/AI of the enemy ship.
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
