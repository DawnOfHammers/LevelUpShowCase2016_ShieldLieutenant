package entities.powerups;

import entities.ship.player.Player;
import entities.shield.OmniShield;

/**
 * Created by Kevin on 12/29/2015.
 */
public class Omni extends Powerup{

    public Omni(int x, int y){
        super(120,x,y,"Bullet.jpg", 120);
    }

    public void activate(Player player){
        System.out.println(timer);
        timer-=1;
        if (timer>0){
            if (player.getShields().size() == 2)
                player.addShield(new OmniShield(new double[]{player.getX(), player.getY()}, 125));
        }else{
            this.deactivate(player);
            player.removeActivePowerup();
        }

    }

    public void deactivate(Player player){
        if(player.getShields().size() == 3)
            player.removeShield(2);
    }

}
