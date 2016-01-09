package entities.ship.Enemies;

import com.badlogic.gdx.scenes.scene2d.Actor;
import entities.projectiles.Bullet;
import entities.projectiles.Missile;
import entities.ship.player.Player;
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * Created by Feng on 12/29/2015.
 */
public class MissileDroid extends Enemy {
    /**Creates a new Droid at points x,y.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    private int firelag;    //After the firing of its weapons a droid will not move for <firelag> seconds.
    public MissileDroid(int x, int y, GameStage gs){
        super(x,y,"Proto.png", gs);
        super.health = 5;
        super.range = 250;
        super.speed = 2;

        super.actions = new boolean[4];

        this.firelag = 0;
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

    private void fire(Player player) {
        firelag++;
        if (firelag == 15) {
            //Creates a new bullet.
            double p_x = player.getX();
            double p_y = player.getY();
            gamestage.addActor(new Missile((int) this.getX(),
                                          (int) this.getY(),
                                          - Math.toDegrees(Math.atan2(p_y - this.getY(), p_x - this.getX())) + 90,
                                          gamestage));

            System.out.println("MissileDroid.fire(): " + Math.toDegrees(Math.atan2(p_y - this.getY(), p_x - this.getX())));
        }
        if (firelag == 30)
            firelag = -1;
    }

    private void firelag(){
        firelag = 1;
    }

    private void target(Player player){
        if ((int)goal_x == (int)this.getX() && (int)goal_y == (int)this.getY()) {
            goal_x = player.getX() - range / 2 + Math.random() * range;
            goal_y = player.getY() - range / 2 + Math.random() * range;
        }
        firelag = 0;
    }
}

