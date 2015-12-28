package gamestates.playState;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import entities.projectiles.*;
import entities.ship.Enemies.Enemy;

import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-09.
 */
public class GameStage extends com.badlogic.gdx.scenes.scene2d.Stage {
    private ArrayList<Actor> enemies = new ArrayList<Actor>();
    private ArrayList<Actor> weapons = new ArrayList<Actor>();
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    public GameStage(Viewport view){
        super(view);
    }

    @Override
    public void draw() {
        super.draw();

    }


    @Override
    public void addActor(Actor actor) {
        //System.out.println(actors.size());
        super.addActor(actor);

        actors.add(actor);
        if (actor instanceof Enemy){
            enemies.add(actor);
        }else if (actor instanceof Weapon){

            weapons.add(actor);
        }
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