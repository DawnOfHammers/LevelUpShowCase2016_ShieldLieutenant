package gamestates.playState;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
<<<<<<< HEAD:Game/core/src/gamestates/playState/GameStage.java
import entities.projectiles.*;
import entities.ship.Enemies.Enemy;
=======
import projectiles.Weapon;
import ship.Enemy;
import ship.Player;
>>>>>>> origin/master:Game/core/src/com/mygdx/game/GameStage.java

import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-09.
 */
public class GameStage extends com.badlogic.gdx.scenes.scene2d.Stage {
    private ArrayList<Actor> enemies = new ArrayList<Actor>();
    private ArrayList<Actor> weapons = new ArrayList<Actor>();
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    private Player player;
    public GameStage(Viewport view){
        super(view);
    }


    @Override
    public void draw() {
        super.draw();

    }


    @Override
    public void addActor(Actor actor){
        //System.out.println(actors.size());
        super.addActor(actor);

        actors.add(actor);
        if (actor instanceof Enemy){
            enemies.add(actor);
        }else if (actor instanceof Weapon){

            weapons.add(actor);
        }else if (actor instanceof Player){
            player = (Player)actor;
        }
    }
    public Player getPlayer() {
        return player;
    }



    public ArrayList<Actor> getEnemies() {
        return enemies;
    }

    public ArrayList<Actor> getWeapons() {
        return weapons;
    }


    public ArrayList<Actor> getActorList() {
        return actors;
    }
}