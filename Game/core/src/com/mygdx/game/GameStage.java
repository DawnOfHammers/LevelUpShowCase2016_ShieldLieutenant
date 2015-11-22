package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import ship.Enemy;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-09.
 */
public class GameStage extends com.badlogic.gdx.scenes.scene2d.Stage {
    private ArrayList<Actor> enemies = new ArrayList<Actor>();
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
        }
    }

    public ArrayList<Actor> getEnemies() {
        return enemies;
    }
}
