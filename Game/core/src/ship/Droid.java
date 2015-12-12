package ship;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameStage;
import projectiles.Bullet;

import java.lang.reflect.Array;
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
        this.range = 10;
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
    protected void aiPlan(GameStage gameStage){
        //TODO All other enemies are.
        ArrayList<Actor> actors = gameStage.getActorList();

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
    protected void aiAct(GameStage game_stage){
        ArrayList<Actor> actors = game_stage.getActorList();

        if(actions[0]){
            firelag--;
            return;
        }
        if(actions[1]) {
            firelag = 180;
            {
                double p_x = actors.get(0).getX();
                double p_y = actors.get(0).getY();
                //System.out.println(this.getX()+"        "+this.getY());
                game_stage.addActor(new Bullet((int) this.getX(), (int) this.getY(), Math.atan2(p_y - this.getY(), p_x - this.getX())));
                //System.out.println(this.getX() + "        " + this.getY());
            }
        }
        if(actions[2]){
            if ((int)goal_x == (int)this.getX() && (int)goal_y == (int)this.getY()) {
                goal_x = actors.get(0).getX() - range/2 + Math.random()*range;
                goal_y = actors.get(0).getY() - range/2 + Math.random()*range;
            }
        }
    }

    @Override
    public void update(GameStage game_screen) {
        super.update(game_screen);
    }
}

