package entities.ship.Enemies;

import com.badlogic.gdx.scenes.scene2d.Actor;
<<<<<<< HEAD:Game/core/src/entities/ship/Enemies/Fighter.java
import entities.projectiles.Bullet;
import entities.projectiles.Laser;
=======
import entities.projectiles.Laser;
import entities.ship.player.Player;
>>>>>>> origin/master:Game/core/src/entities/ship/Enemies/Fighter.java
import gamestates.playState.GameStage;

import java.util.ArrayList;

/**
 * Created by Feng on 12/29/2015.
 */
public class Fighter extends Enemy {
    /**Creates a new Fighter at points x,y.
     *
     * @param x X - Cord
     * @param y Y - Cord
     */
    private double tg_angle;
    private int turn_rate;
    private int cool_down;
    public Fighter(int x, int y, GameStage gs){
        super(x,y,"Proto.png", gs);
        super.health = 5;
        super.speed = 3;
        super.range = 250;

        super.actions = new boolean[4];

	    this.tg_angle = super.angle;
        this.turn_rate = 3;
        this.cool_down = 0;
    }

    /**
     * Action 0: [Fire].
     *           - In range.
     *           - Shots Lined Up.
     *           - Weapon reloaded.
     *
     * Action 1: [Coming].
     *
     * Action 2: [Going].
     *
     * Action 3: [Moving].
     */
    @Override
    protected void aiPlan(){
        ArrayList<Actor> actors = gamestage.getActorList();

        boolean in_range = range * range > Math.pow(actors.get(0).getX() - this.getX(), 2)
                                        + Math.pow(actors.get(0).getY() - this.getY(), 2);
	

        actions[0] = in_range && (int)angle == (int)tg_angle && cool_down == 0;
        actions[1] = cool_down == 0;
        actions[2] = cool_down != 0;

	    actions[3] = health > 2;
    }

    /**Action 0: 
     *
     * Action 1: 
     *
     * Action 2: 
     * 
     * Action 3: 
     */
    @Override
    protected void aiAct(){
        Player player = gamestage.getPlayer();

        tg_angle = direction(player);
        
        int turn = shortSide();

        if(actions[0]){
            fire(player.getX(), player.getY());
        }
        if(actions[1]){
            turn *= 3;
        }
        if(actions[2]){
            turn *= -1;
            cool_down --;
        }
	    if(actions[3]){
            angle += turn * turn_rate;
            super.moveAngle();
	    }
    }

    private void fire(double p_x, double p_y){
        gamestage.addActor(new Laser((int) this.getX(),
                (int) this.getY(),
                - Math.atan2(p_y - this.getY(),
                        p_x - this.getX()) + 90,
                gamestage));
        cool_down = 300;
    }


    private double direction(Player player){
        double dx = (player.getX() - this.getX()), dy = (player.getY() - this.getY());
        return -Math.toDegrees(Math.atan2(dy, dx)) + 90;
    }

    private int shortSide(){
        boolean inside;
        angle = (angle + 360) % 360;
        tg_angle = (tg_angle + 360) % 360;
        inside = Math.abs(angle - tg_angle) < Math.min(angle, tg_angle) - Math.max(angle, tg_angle) + 360;
        if(inside == (tg_angle > angle))
            return 1;
        return -1;
    }
}

