package entities.powerups;

import entities.ship.player.Player;
/**
 * Created by Kevin on 12/29/2015.
 */
public class Afterburner extends Powerup{

    public Afterburner(int x, int y){
        super(120,x,y,"Bullet.jpg", 120, 0);
    }

    public void activate(Player player){
        timer-=1;
        if(this.active) {
            if (timer > 0) {
                player.speed = 8;
            } else {
                this.deactivate(player);
                this.active = false;
            }
        }
    }

    public void deactivate(Player player){
        if(player.speed > 4)
            player.speed = 4;
    }

}
