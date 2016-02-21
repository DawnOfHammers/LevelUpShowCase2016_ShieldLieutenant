package entities.powerups;

import entities.ship.player.Player;

/**
 * Created by Kevin on 12/29/2015.
 */
public class Regen extends Powerup{

    public Regen(int x, int y){
        super(120,x,y,"Bullet.jpg",0, 1);
    }

    public void activate(Player player){
        player.setHealth(-60);
        this.deactivate(player);
    }

    public void deactivate(Player player){
        this.active = false;
    }

}
