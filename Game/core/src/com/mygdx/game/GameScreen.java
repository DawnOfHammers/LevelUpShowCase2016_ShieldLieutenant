package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import projectiles.Bullet;
import projectiles.Weapon;
import ship.Droid;
import ship.Player;
import ship.Proto;
import ship.Ship;

/**
 * Created by Hairuo on 2015-11-17.
 */

public class GameScreen implements Screen {
    GameStage gStage;
    Test test;
    Player player;
    Droid proto;
    int bound_x;
    int bound_y;
    private Game game;
    double accumulator = 0;
    double step_time = (double)1/60;
    double time = 0;
    double shoot_timer= 0; //just for testing


    public GameScreen(Game game, int bound_x,int bound_y){
        this.game = game;
        Gdx.graphics.setDisplayMode(1366, 768, false);
        this.player = new Player(100,300);
        this.test = new Test();
        this.proto = new Droid(100,100);
        this.bound_x = bound_x;
        this.bound_y = bound_y;
        ScreenViewport viewport = new ScreenViewport();
        gStage = new GameStage(viewport);

        Gdx.input.setInputProcessor(gStage);

        gStage.addActor(player);
        //gStage.addActor(test);
        gStage.addActor(proto);
        gStage.setKeyboardFocus(player);
    }

    public void updateCamera(Stage stage){ //locks the camera onto the player
        int xOffest = (int)player.getSprite().getWidth()/2;
        int yOffest = (int)player.getSprite().getHeight()/2;
        stage.getViewport().getCamera().position.set((int) player.getX() + xOffest, (int) player.getY() + yOffest, 0);
    }

    public boolean checkBounds(int x,int y){ //checks if the spaceship is within the set arena

        return (x > bound_x || y > bound_y || x  < -bound_x || y < -bound_y);

    }

    public void updateGame(){
        shoot_timer++;
        updateCamera(gStage);
        for (Actor i : gStage.getActors()){
            if(i instanceof Player){
                ((Player) i).update(gStage);
                if (checkBounds((int)i.getX(),(int)i.getY())){
                    ((Player) i).setHealth(-1);
                }
            }
            if(i instanceof Bullet){
                ((Bullet) i).update();
            }
            if(i instanceof Weapon){
                if(checkBounds((int)i.getX(),(int)i.getY())){
                    i.remove();
                }
            }
            if(i instanceof Droid){
                //System.out.println("ASDASDASD");
                //System.out.println(gStage.getActorList().size()+"a");
                ((Droid) i).update(gStage);
            }
        }
        if(shoot_timer%120 == 0){
            shoot();
        }
    }


    @Override
    public void show() {

    }

    public void shoot(){
        gStage.addActor(new Bullet(100,100,90));
        gStage.addActor(new Bullet(100,100,270));
    }

    @Override
    public void render(float delta){
        accumulator += delta;
        while (accumulator >= step_time){

            updateGame();
            accumulator -= step_time;
            time+=step_time;
        }

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gStage.act(0.166f);
        gStage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
