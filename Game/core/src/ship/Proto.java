package ship;



import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

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
        super(x,y);
        super.health = 5;
        super.actions = new boolean[2];
        super.sprite = new Sprite(new Texture(("Proto.png")));
    }

    /**Moves to a target location in a straight line.
     *
     */
    @Override
    protected void move() {
        int speed = 2;
        double dx = (goalX - this.getX()), dy = (goalY - this.getY());
        double hyp = Math.hypot(dx, dy);

        {// Ship Movement
            this.getX() += speed * dx / hyp;
            this.getY() += speed * dy / hyp;
        }
    }

    /**Action 0: When hp is above 2.
     *
     * Action 1: When hp is under 2.
     *
     */
    @Override
    protected void behavior(){
        actions[0] = health > 2;
        actions[1] = health <=2;
    }

    /**Action 0: Move to a random position.
     *
     * Action 1: Stop moving.
     */
    @Override
    protected void action(){
        if(actions[0]) {
            //TODO change implementation of coordsX and Y in ship.
            if ((int)goalX == (int)this.getX() && (int)goalY == (int)this.getY()) {
                goalX = (int)(Math.random()*1000);
                goalY = (int)(Math.random()*800);
            }
        }
        if(actions[1]){
            goal_x = super.getY();
            goal_y = super.getY();
        }
        //TODO implement
    }

    @Override
    public void update() {

    }
}
