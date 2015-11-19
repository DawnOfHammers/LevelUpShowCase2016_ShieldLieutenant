package ship;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by Feng on 11/18/2015.
 */
public class Droid extends Enemy {
    /**Creates a new Droid at points x,y.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    public Droid(int x, int y){
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
        double dx = (goal_x - this.getX()), dy = (goal_y - this.getY());
        double hyp = Math.hypot(dx, dy);

        {// Ship Movement
            this.setX(this.getX() + (float)(speed * dx / hyp));
            this.setY(this.getY() + (float)(speed * dy / hyp));
        }
    }

    /**Action 0: When hp is above 2.
     *
     * Action 1: When hp is under 2.
     *
     */
    @Override
    protected void aiPlan(){
        //TODO
    }

    /**Action 0: TODO
     *
     * Action 1: TODO
     */
    @Override
    protected void aiAct(){
        if(actions[0]) {
            //TODO Action 2
        }
        if(actions[1]){
            //TODO Action 1
        }
    }
}

