package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gamestates.GameStateManager;

/**
 * Created by Hongyu Wang on 12/27/2015.
 */
public class MainGame extends ApplicationAdapter{
    public static final int FPS = 60;
    public static int WIDTH;
    public static int HEIGHT;
    private SpriteBatch batch;
    private long difference, start = System.currentTimeMillis();
    public static int [] relevant_inputs = {
            Input.Keys.Q,
            Input.Keys.W,
            Input.Keys.E,
            Input.Keys.R,
            Input.Keys.UP,
            Input.Keys.LEFT,
            Input.Keys.RIGHT
    };

    public static OrthographicCamera cam;

    private GameStateManager gsm;

    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        batch = new SpriteBatch();
        gsm = new GameStateManager(batch);
    }


    public void render() {
        sleep();
        batch.begin();
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
        batch.end();
    }




    public void resize(int width, int height){

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose(){
        gsm.dispose();
        batch.dispose();
    }

    public void sleep() {
        difference = System.currentTimeMillis() - start;
        long targetDelay = 1000/FPS;
        if (difference < targetDelay) {
            try{
                Thread.sleep(targetDelay - difference);
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        start = System.currentTimeMillis();
    }
}
