package entities.powerups;

import entities.ship.player.Player;

/**
 * Created by Kevin on 12/29/2015.
 */
public class TimeSlow extends Powerup{

    public TimeSlow(int x, int y){
        super(120,x,y,"slow.jpg", 120,4);
    }

    public void activate(Player player){

    }

    public void deactivate(Player player){
        if (timer<0){
            player.speed = 0;
        }
    }

}
