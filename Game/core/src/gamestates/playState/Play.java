package gamestates.playState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import entities.projectiles.Bullet;
import entities.projectiles.Laser;
import entities.projectiles.Missile;
import entities.ship.player.Player;
import game.MainGame;
import gamestates.GameState;
import gamestates.GameStateManager;
import gamestates.TestActor;
import java.util.Hashtable;

/**
 * Created by Hongyu Wang on 12/27/2015.
 *
 * This is the <Play></Play> GameState. This is the primary control class of the game.
 */
public class Play extends GameState{
    private GameStage gStage;
    private Player main_player;
    private Laser laser;
    private Bullet bullet;
    private Missile missile;
    private int timer;



    public static Hashtable<Integer, Boolean> key_events;
    public static OrthographicCamera cam;
    public static int [] relevant_inputs = {
            Input.Keys.Q,
            Input.Keys.W,
            Input.Keys.E,
            Input.Keys.R,
            Input.Keys.UP,
            Input.Keys.LEFT,
            Input.Keys.RIGHT,
            Input.Keys.T
    };

    public Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        cam = new OrthographicCamera(MainGame.WIDTH, MainGame.HEIGHT);
        ScreenViewport viewport = new ScreenViewport(cam);
        gStage = new GameStage(viewport);

        laser = new Laser(200,200, 220, gStage);
        bullet = new Bullet(100,-200,0,gStage);
        missile = new Missile(100,100,0, gStage);

        key_events = new Hashtable<Integer, Boolean>();
        for (int input : relevant_inputs){
            key_events.put(input, false);
        }


        //gStage.addActor(laser);
        Actor test = new TestActor();
        test.setX(0);
        test.setY(0);
        //gStage.addActor(test);
        //gStage.addActor(bullet);
        gStage.addActor(missile);
        gStage.addActor(main_player = new Player(200,100, gStage));




        gStage.addActor(test);
        gStage.getPlayer().updateCamera();

    }

    @Override
    public void update(float dt) {

        timer++;
        spawnBullet();

        handleInput();
        gStage.act(dt);

    }



    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 0);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gStage.draw();


    }

    @Override
    public void handleInput() {
        for (int i : Play.key_events.keySet()){
            Play.key_events.replace(i, Gdx.input.isKeyPressed(i));
        }




        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            gsm.setState(GameStateManager.MENU);
        }
    }

    @Override
    public void dispose() {
        gStage.dispose();
    }


    public void spawnBullet() {
        if (timer % 60 == 0) {
            Bullet bullet_1 = new Bullet(100, 100, 0, gStage);
            Bullet bullet_2 = new Bullet(100, 100, 180, gStage);
            gStage.addActor(bullet_1);
            gStage.addActor(bullet_2);
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {


    }

}
