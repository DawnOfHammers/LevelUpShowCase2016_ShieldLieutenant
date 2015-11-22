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
    public Droid(int x, int y){
        super(x,y,"Proto.png");
        super.health = 5;
        super.actions = new boolean[2];
    }

    /**Action 0: When hp is above 2.
     *
     * Action 1: When hp is under 2.
     *
     */
    @Override
    protected void aiPlan(ArrayList<Actor> actors){
        //TODO All other enemies are.
        // BAsIC LOGIC using co-ords
    }

    /**Action 0: TODO
     *
     * Action 1: TODO
     */
    @Override
    protected void aiAct(ArrayList<Actor> actors){
        if(actions[0]) {
            //TODO Action 2
        }
        if(actions[1]){
            //TODO Action 1
        }
    }

    @Override
    public void update(ArrayList<Actor> actors) {
        super.update(actors);
    }
}

