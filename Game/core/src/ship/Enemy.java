package ship;

import ship.Ship;

/**A generic Enemy superclass. All Enemy(s) are Ship(s).
 *
 * Created by Feng on 11/14/2015.
 */
public abstract class Enemy extends Ship {
    boolean[] actions;

    /**Initiates the location of an enemy and is the constructor of the Generic Enemy superclass.
     *
     * This constructor should never be called in isolation.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    protected Enemy(int x, int y){
        super(x, y);
    }

    /**The Logic/AI of the enemy ship.
     * Mutates the boolean array actions.
     */
    protected abstract void behavior();

    /**The implementation of individual enemy action.
     * Updates the enemy based on the boolean array actions.
     *      if     (actions[0]){...}
     *      else if(actions[1]){...}
     *      else if(actions[2]){...}
     */
    protected abstract void action();
}
