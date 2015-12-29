package gamestates.playState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
    public static Hashtable<Integer, Boolean> key_events;
    public static OrthographicCamera cam;

    public Play(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        cam = new OrthographicCamera(MainGame.WIDTH, MainGame.HEIGHT);
        ScreenViewport viewport = new ScreenViewport(cam);
        gStage = new GameStage(viewport);

        key_events = new Hashtable<Integer, Boolean>();
        for (int input : MainGame.relevant_inputs){
            key_events.put(input, false);
        }
        Actor test = new TestActor();
        test.setX(0);
        test.setY(0);
        gStage.addActor(test);
        gStage.addActor(main_player = new Player(100,300, gStage));

    }

    @Override
    public void update(float dt) {
        handleInput();
        gStage.act(dt);

    }



    @Override
    public void draw() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gStage.draw();
    }

    @Override
    public void handleInput() {
        for (int i : Play.key_events.keySet()){
            Play.key_events.replace(i, Gdx.input.isKeyPressed(i));
        }
    }

    @Override
    public void dispose() {
        gStage.dispose();
    }

}
