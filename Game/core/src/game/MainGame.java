package game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.managers.GameStateManager;
import entities.managers.InputManager;

/**
 * Created by Hongyu Wang on 12/27/2015.
 */
public class MainGame extends ApplicationAdapter{
    private static final int FPS = 60;
    public static int WIDTH;
    public static int HEIGHT;
    private long start = System.currentTimeMillis();

    private InputManager input_manager;
    public static OrthographicCamera cam;

    private GameStateManager gsm;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        SpriteBatch batch = new SpriteBatch();
        gsm = new GameStateManager(batch, this);
        input_manager = new InputManager();
        Gdx.input.setInputProcessor(input_manager);
    }

    @Override
    public void render() {
        sleep();
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.draw();
    }

    @Override
    public void resize(int width, int height){

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose(){
        gsm.dispose();
    }

    /* This is a method that will sleep the current application adapter thread. This is done mainly to cap the FPS.
     *
     */
    private void sleep() {
        long difference = System.currentTimeMillis() - start;
        long targetDelay = 1000/FPS;
        if (difference < targetDelay) {
            try{
                Thread.sleep(targetDelay - difference);
            } catch (InterruptedException e) {e.printStackTrace();}
        }
        start = System.currentTimeMillis();
    }





    /**
     * A public static method that converts a GDX angle into an angle in radians.
     * @param angle Angle in LIBGDX degrees.
     * @return Angle in Radians.
     */
    public static float fromGDXAngle(float angle){
        float return_angle = (float)Math.toRadians(-angle + 90);
        while (return_angle < 0){
            return_angle += Math.PI*2;
        }
        return return_angle;

    }

    /**
     * A public static method that converts an angle in radians into an angle in LibGDX degrees.
     * @param angle Angle in Radians.
     * @return Angle in LibGDX degrees.
     */
    public static float toGDXAngle(float angle){
        float return_angle = -(float)Math.toDegrees(angle) + 90;
        while (return_angle < 0){
            return_angle += 360;
        }
        return return_angle;
    }
}
