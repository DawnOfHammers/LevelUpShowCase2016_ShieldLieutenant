package entities.ship.Enemies;

import com.badlogic.gdx.scenes.scene2d.Actor;


import entities.projectiles.Laser;
import entities.ship.player.Player;

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
        super(x,y,"Fighter.png", gs);
        super.health = 5;
        super.speed = 3;
        super.range = 500;

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
        boolean aim = Math.abs((int)angle - (int)tg_angle) < 5;
	
        actions[0] = in_range && aim && cool_down == 0;
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
            fire();
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

    private void fire(){
        gamestage.addActor(new Laser((int) this.getX(),
                                     (int) this.getY(),
                                     this.angle,
                                     gamestage,
                                     3600d));

        cool_down = 100;
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

