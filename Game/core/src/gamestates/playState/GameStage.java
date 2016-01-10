package gamestates.playState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import entities.projectiles.*;
import entities.ship.Enemies.Enemy;
import entities.ship.player.Player;


import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-09.
 */
public class GameStage extends com.badlogic.gdx.scenes.scene2d.Stage {
    private ArrayList<Actor> enemies = new ArrayList<Actor>();
    private ArrayList<Actor> weapons = new ArrayList<Actor>();
    private ArrayList<Actor> actors = new ArrayList<Actor>();
    private ArrayList<Actor> remove = new ArrayList<Actor>();
    private Player player;
    private ArrayList<ParticleEffect> effects;
    private ParticleEffectPool pool;

    public GameStage(Viewport view){
        super(view);


    }


    @Override
    public void draw() {
        super.draw();



    }

    @Override
    public void act(float delta) {
        super.act(delta);



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

    public void deleteActor(Actor actor){
        remove.add(actor);
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

    public void addEffect(ParticleEffect effect) {
        effects.add(effect);
    }

    public ParticleEffectPool getPool() {
        return pool;
    }
}