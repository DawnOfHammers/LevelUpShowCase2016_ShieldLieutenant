package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ship.Player;
import ship.Ship;

import java.sql.Time;

/**
 * Created by Hairuo on 2015-11-17.
 */
public class GameScreen implements Screen {
    GameStage gStage;
    Test test;
    Player player;
    int bound_x;
    int bound_y;
    private Game game;
    double accumulator = 0;
    double step_time = (double)1/60;
    double time = 0;
    double current_time = 0;


    public GameScreen(Game game){
        this.game = game;
        Gdx.graphics.setDisplayMode(1366, 768, false);
        player = new Player(100,100);
        test = new Test();
        ScreenViewport viewport = new ScreenViewport();
        gStage = new GameStage(viewport);

        Gdx.input.setInputProcessor(gStage);

        gStage.addActor(player);
        gStage.addActor(test);
        gStage.setKeyboardFocus(player);
    }

    public void updateCamera(Stage stage){ //locks the camera onto the player
        int xOffest = (int)player.getSprite().getWidth()/2;
        int yOffest = (int)player.getSprite().getHeight()/2;
        stage.getViewport().getCamera().position.set((int) player.getX() + xOffest, (int) player.getY() + yOffest, 0);
    }

    public boolean checkBounds(int x,int y){ //checks if the spaceship is within the set arena
        return !(x > bound_x || y > bound_y || x  < -bound_x || y < -bound_y);

    }

    public void updateGame(){
        updateCamera(gStage);
        checkBounds(bound_x,bound_y);
        for (Actor i : gStage.getActors()){
            if(i instanceof Ship){
                ((Ship) i).update();
                if (checkBounds((int)i.getX(),(int)i.getY())){
                    ((Ship) i).setHealth(-1);
                }
            }
        }

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        accumulator += delta;
        while (accumulator >= step_time){

            updateGame();
            System.out.println(accumulator);
            System.out.println(step_time);
            System.out.println("a");
            accumulator -= step_time;
            time+=step_time;
        }

        System.out.println("b");
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
