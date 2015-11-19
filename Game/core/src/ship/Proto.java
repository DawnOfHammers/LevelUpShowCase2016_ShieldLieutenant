package ship;


/**Prototype of a basic enemy ship type.
 * Created by Feng on 11/14/2015.
 */
public class Proto extends Enemy {
    /**Creates a new Proto at points x,y.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    public Proto(int x, int y){
        super(x,y, "Proto.png");
        super.health = 5;
        super.actions = new boolean[2];
    }

    /**Action 0: When hp is above 2.
     *
     * Action 1: When hp is under 2.
     *
     */
    @Override
    protected void aiPlan(){
        actions[0] = health > 2;
        actions[1] = health <=2;
    }

    /**Action 0: Move to a random position.
     *
     * Action 1: Stop moving.
     */
    @Override
    protected void aiAct(){
        if(actions[0]) {
            //TODO change implementation of coordsX and Y in ship.
            if ((int)goal_x == (int)this.getX() && (int)goal_y == (int)this.getY()) {
                goal_x = (int)(Math.random()*1000);
                goal_y = (int)(Math.random()*800);
            }
        }
        if(actions[1]){
            goal_x = super.getY();
            goal_y = super.getY();
        }
    }
}
