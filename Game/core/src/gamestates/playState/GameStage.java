package gamestates.playState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import entities.projectiles.*;
import entities.ship.Enemies.Enemy;
import entities.ship.player.Player;


import java.util.ArrayList;

/**
 * Created by Hairuo on 2015-11-09.
 */
public class GameStage extends com.badlogic.gdx.scenes.scene2d.Stage {
    /**
     * Arraylist of enemy actors
     */
    private ArrayList<Actor> enemies = new ArrayList<Actor>();

    /**
     * Arraylist of weapon actors
     */
    private ArrayList<Actor> weapons = new ArrayList<Actor>();

    private ArrayList<Actor> actors = new ArrayList<Actor>();

    /**
     * Arraylist of actors that need to be removed
     */
    private ArrayList<Actor> remove = new ArrayList<Actor>();

    /**
     * Actor of the player
     */
    private Player player;

    /**
     * X Boundary of the game
     */
    private double x_bound;

    /**
     * Y Boundary of the game
     */
    private double y_bound;

    /**
     * Shape renderer used to renderer the boundaries of the game
     * only a test for now
     */

    private ShapeRenderer shapeRenderer;

    /**
     * whether or not to rgb shift the images
     */
    public boolean distort;

    public GameStage(Viewport view, double x, double y){
        super(view);
        distort = false;
        shapeRenderer = new ShapeRenderer();
        this.x_bound = x;
        this.y_bound = y;


    }


    @Override
    public void draw() {
        super.draw();




        shapeRenderer.setProjectionMatrix(this.getBatch().getProjectionMatrix());
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.rect(-(float) x_bound, -(float) y_bound, (float) x_bound * 2, (float) y_bound * 2);
        shapeRenderer.end();







    }



    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void checkBounds(Actor actor){
        double x_value = actor.getX();
        double y_value = actor.getY();
        if(x_value < -x_bound || y_value < -y_bound || x_value > x_bound || y_value > y_bound){
            if(actor == player){
                //TODO make stuff happen here
                distort = true;
                ((Player) actor).setHealth(-50);
            }else{
                 
            }
        }else{
            if(actor == player){
                distort = false;
            }
        }
    }

    @Override
    public void addActor(Actor actor){
        actors.add(actor);
        //System.out.println(this.getActors().size);
        super.addActor(actor);
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

    public ArrayList<Actor> getEnemies(){ return enemies;}

    public ArrayList<Actor> getActorList() {
        return actors;
    }

    public ArrayList<Actor> getWeapons() {
        return weapons;
    }

    public ArrayList<Actor> getRemove() {
        return remove;
    }


}