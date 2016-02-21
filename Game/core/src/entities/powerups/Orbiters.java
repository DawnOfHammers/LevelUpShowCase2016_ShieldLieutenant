package entities.powerups;

import entities.ship.player.Player;
import entities.shield.OrbitShield;

/**
 * Created by Kevin on 12/29/2015.
 */
public class Orbiters extends Powerup{

    private boolean start;

    public Orbiters(int x, int y){
        super(120,x,y,"Bullet.jpg", 600,2);
        start = false;
    }

    public void activate(Player player){
        if(!start) {
            player.addShield(2, new OrbitShield((int) player.getX(), (int) player.getY(), player.getGameStage(), 125, "orange", "bullet"));
            start = true;
        }
    }

    public void deactivate(Player player){
        if(active) {
            if (start)
                timer -= 1;
            //System.out.println(timer);
            if (timer < 0 || !active &&  player.getShields().size()>2) {
                active = false;
                player.removeShield(2);
            }
        }

    }

}
