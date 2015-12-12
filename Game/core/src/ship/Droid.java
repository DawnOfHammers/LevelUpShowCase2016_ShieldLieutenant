package ship;

import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by Feng on 11/18/2015.
 */
public class Droid extends Enemy {
    /**Creates a new Droid at points x,y.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    private int range;
    private int firelag;    //After the firing of its weapons a droid will not move for <firelag> seconds.
    public Droid(int x, int y){
        super(x,y,"Proto.png");
        super.health = 5;
        super.actions = new boolean[3];
        this.range = 300;
        this.firelag = 0;
    }

    /**
     * Action 0: When droid is suffering from weapon's lag.
     *
     * Action 1: When "player" is within range: 300.
     *
     * Action 2: When "player" is not within range.
     */
    @Override
    protected void aiPlan(ArrayList<Actor> actors){
        //TODO All other enemies are.
        actions[0] = firelag > 0;
        actions[1] = range > Math.pow(actors.get(0).getX() - this.getX(), 2)
                         + Math.pow(actors.get(0).getY() - this.getY(), 2);
        actions[2] = !actions[1];
    }

    /**Action 0: Prevents the droid from doing any actions for <firelag> number of frames.
     *
     * Action 1: When "player" is within range fire. Droid will now lag for: 10
     *
     * Action 2: When "player" is not within range. Droid will move to a location close to "player".
     *           NOTE: Variation of goal_x, and goal_y should not be above 1.4 or root(2).
     */
    @Override
    protected void aiAct(ArrayList<Actor> actors){
        if(actions[0]){
            firelag--;
            return;
        }
        if(actions[1]){
            firelag = 10;
            //TODO fire laser/weapons.
        }
        if(actions[2]){
            if ((int)goal_x == (int)this.getX() && (int)goal_y == (int)this.getY()) {
                goal_x = actors.get(0).getX() - range/2 + Math.random()*range;
                goal_y = actors.get(0).getY() - range/2 + Math.random()*range;
            }
        }
    }
}

