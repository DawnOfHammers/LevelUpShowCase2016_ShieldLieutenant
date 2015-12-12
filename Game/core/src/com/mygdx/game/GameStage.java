package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import projectiles.Weapon;
import ship.Enemy;

import java.lang.reflect.Array;
import java.nio.file.Watchable;
import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-09.
 */
public class GameStage extends com.badlogic.gdx.scenes.scene2d.Stage {
    private ArrayList<Actor> enemies = new ArrayList<Actor>();
    private ArrayList<Actor> weapons = new ArrayList<Actor>();
    public GameStage(Viewport view){
        super(view);
    }

    @Override
    public void draw() {
        super.draw();

    }


    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);
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


}
