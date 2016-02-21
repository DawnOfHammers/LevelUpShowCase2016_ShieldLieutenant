package entities.powerups;

import entities.ship.player.Player;

/**
 * Created by Kevin on 12/29/2015.
 */
public class Well extends Powerup{

    public Well(int x, int y){
        super(120,x,y,"well.jpg", 120,5);
    }

    public void activate(Player player){

    }

    public void deactivate(Player player){
        if (timer<0){
            player.speed = 0;
        }
    }

}
