package entities.ship.Enemies;

import gamestates.playState.GameStage;

/**
 * Created by Kevin Zheng on 2016-02-15.
 */
public class BulletBoss extends Enemy {
    public BulletBoss(int x, int y, GameStage gs){
        super(x, y, "Proto.png", gs);
    }

    @Override
    protected void init(){
        super.health = 100;
        super.range = 500;
        super.speed = 1;
    }

    @Override
    public void aiPlan(){

    }

    @Override
    public void aiAct(){

    }
}
