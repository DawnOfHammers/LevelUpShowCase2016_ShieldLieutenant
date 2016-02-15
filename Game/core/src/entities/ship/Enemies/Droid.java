package entities.ship.Enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.projectiles.Bullet;
import entities.ship.player.Player;
import gamestates.playState.GameStage;
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
    private int firelag;    //After the firing of its weapons a droid will not move for <firelag> seconds.
    public Droid(int x, int y, GameStage gs, String sprite_name){
        super(x,y,sprite_name, gs);

        this.firelag = 1;
    }

    @Override
    protected void init(){
        super.health = 5;
        super.range = 750;
        super.speed = 4;

        super.actions = new boolean[4];
    }

    /**
     * Action 0: When droid is suffering from weapon's lag.
     *
     * Action 1: When "player" is within range: 300.
     *
     * Action 2: When "player" is not within range.
     *
     * Action 3: When not under weapon's lag.
     */
    @Override
    protected void aiPlan(){
        //TODO All other enemies are.
        Player player = gamestage.getPlayer();

	    boolean stop = (int)goal_x == (int)this.getX() && (int)goal_y == (int)this.getY();
	    boolean inrange = range * range > Math.pow(player.getX() - this.getX(), 2)
                                        + Math.pow(player.getY() - this.getY(), 2);

        actions[0] = firelag > 0;
        actions[1] = inrange && stop;
        actions[2] = (!inrange) && stop;
	    actions[3] = health > 2;
    }

    /**Action 0: Prevents the droid from doing any actions for <firelag> number of frames.
     *
     * Action 1: When "player" is within range fire. Droid will now lag for: 10
     *
     * Action 2: When "player" is not within range. Droid will move to a location close to "player".
     *           NOTE: Variation of goal_x, and goal_y should not be above 1.4 or root(2).
     * 
     * Action 3: Moving.
     */
    @Override
    protected void aiAct(){
        Player player = gamestage.getPlayer();

        if(actions[0]){
            fire(player);
            return;
        }
        if(actions[1]) {
            firelag();
        }
        if(actions[2]){
            target(player);
        }
	    if(actions[3]){
	        super.movePoint();
	    }
    }

    private void fire(Player player){

        firelag++;
        if(firelag == 5){
            //Creates a new bullet.
            double p_x = player.getX();
            double p_y = player.getY();
            gamestage.addActor(new Bullet((int) this.getX(),
                                          (int) this.getY() ,
                                          - Math.toDegrees(Math.atan2(p_y - this.getY(), p_x - this.getX())) + 90,
                                          gamestage,"Bullet"));
        }
        if(firelag == 10) {
            firelag = -1;
        }

    }

    private void firelag(){
	firelag = 1;
    }

    private void target(Player player){
        if ((int)goal_x == (int)this.getX() && (int)goal_y == (int)this.getY()) {
            goal_x = player.getX() + ((int)(Math.random()*2) - 1) * (Math.random() * range/2 + range/4);
            goal_y = player.getY() + ((int)(Math.random()*2) - 1) * (Math.random() * range/2 + range/4);
        }
        firelag = 0;
    }
}

