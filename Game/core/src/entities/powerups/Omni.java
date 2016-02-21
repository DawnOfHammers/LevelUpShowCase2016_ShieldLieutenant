package entities.powerups;

import entities.ship.player.Player;
import entities.shield.OmniShield;

/**
 * Created by Kevin on 12/29/2015.
 */
public class Omni extends Powerup{

    static OmniShield shield;

    public Omni(int x, int y){
        super(120,x,y,"Bullet.jpg", 120, 1);

        internal_cooldown = 5;

    }

    public void activate(Player player){

        timer-=1;
        if (timer>0) {
            internal_cooldown = 1;
            if (player.getShields().size()%2 == 0) {
                player.addShield(2, new OmniShield((int) player.getX(), (int) player.getY(), player.getGameStage(), 100, "red", "bullet"));
            }
        }else {
            deactivate(player);
            active = false;
            internal_cooldown = 0;
        }

    }

    public void deactivate(Player player){
        internal_cooldown-=1;

        if((internal_cooldown < 0 || !active) && player.getShields().size()>2)
            player.removeShield(2);
    }

}