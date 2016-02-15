package gamestates.playState;

import box2dLight.ConeLight;
import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import entities.Background.Background;
import entities.managers.InputManager;
import entities.projectiles.Bullet;
import entities.projectiles.Laser;
import entities.projectiles.Missile;
import entities.ship.Enemies.Droid;
import entities.ship.Enemies.Enemy;
import entities.ship.player.Player;
import game.MainGame;
import gamestates.GameState;
import entities.managers.GameStateManager;
import gamestates.TestActor;
import javafx.scene.PointLight;
import javafx.scene.effect.LightBuilder;

import java.awt.*;
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

    private Enemy enemy;


    private int timer;

    //Box 2d light testing
    private World world;
    private Box2DDebugRenderer debug_renderer;
    private RayHandler ray_handler;
    private RayHandler ray_handler2;
    private Body body;
    private SpriteBatch batch;
    private Sprite sprite;
    private Body player;
    private Background background;

    public static Hashtable<Integer, Boolean> key_events;
    public static OrthographicCamera cam;


    public Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        cam = new OrthographicCamera(MainGame.WIDTH, MainGame.HEIGHT);
        cam.setToOrtho(false, MainGame.WIDTH, MainGame.HEIGHT);
        ScreenViewport viewport = new ScreenViewport(cam);
        sprite = new Sprite(new Texture(("S2.png")));
        gStage = new GameStage(viewport);

        laser = new Laser(0, 0, 270, gStage, 3600);
        bullet = new Bullet(100,-200,0,gStage);
        missile = new Missile(100,100,0, gStage);

        enemy = new Droid(500, 500, gStage);




        //gStage.addActor(laser);
        Actor test = new TestActor();
        test.setX(0);
        test.setY(0);
        //gStage.addActor(test);
        gStage.addActor(bullet);
        gStage.addActor(missile);
        gStage.addActor(main_player = new Player(200, 100, gStage));
        gStage.addActor(enemy);


        //gStage.addActor(test);
        gStage.getPlayer().updateCamera();



        //Box2dLights testing

        this.world = new World(new Vector2(0, 0), false);
        this.debug_renderer = new Box2DDebugRenderer();
        //background = new Background(world, gStage);


        //RayHandler.setGammaCorrection(true);
        //RayHandler.useDiffuseLight(true);

        ray_handler = new RayHandler(world);
        ray_handler2 = new RayHandler(world);
        ray_handler2.setAmbientLight(1f);
        ray_handler.setAmbientLight(1f);
        ray_handler.setShadows(false);




        box2dLight.PointLight test_light1 = new box2dLight.PointLight(ray_handler2, 50, Color.RED, 700, -200, -400);
        box2dLight.PointLight test_light2 = new box2dLight.PointLight(ray_handler, 50, Color.ORANGE, 1000, 500, -500);
        box2dLight.PointLight test_light3 = new box2dLight.PointLight(ray_handler2, 50, Color.CYAN, 300, 0, 500);
        box2dLight.PointLight test_light4 = new box2dLight.PointLight(ray_handler,50, Color.LIME , 1000, -500,200);
        box2dLight.PointLight test_light5 = new box2dLight.PointLight(ray_handler,50, Color.PURPLE , 1000, 500,500);




        batch = new SpriteBatch();

        //test_light1.dispose();
        //test_light2.dispose();
        //test_light3.dispose();
        //test_light4.dispose();
        //test_light5.dispose();


    }

    @Override
    public void update(float dt) {
        world.step(1 / 60f, 6, 2);
        //background.update();
        ray_handler.update();
        ray_handler2.update();


        timer++;
        spawnBullet();

        handleInput();
        gStage.act(dt);

        ray_handler.setCombinedMatrix(cam);
        ray_handler2.setCombinedMatrix(cam);

    }



    @Override
    public void draw() {

        //Gdx.gl.glClearColor(146/255, 188/255, 213/255, 1);
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);






        for (Actor remove : gStage.getRemove()){
            gStage.getActors().removeIndex(gStage.getActors().indexOf(remove, true));
        }
        //System.out.println(gStage.getActorList().size());
        gStage.getRemove().clear();

        debug_renderer.render(world, cam.combined);


        ray_handler.render();
        ray_handler2.render();

        gStage.draw();

    }







    @Override
    public void handleInput() {
        if (InputManager.get_input_state(InputManager.RETURN_TO_MENU)){
            gsm.setState(GameStateManager.MENU);
        }
    }

    @Override
    public void dispose() {

        ray_handler.dispose();
        gStage.dispose();
        world.dispose();
        debug_renderer.dispose();

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
