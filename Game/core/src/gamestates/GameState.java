package gamestates;

import com.badlogic.gdx.Gdx;

/**
 * Created by Hongyu Wang on 12/27/2015.
 *
 *  The GameState class has one major purposes: to act as a replacement "screen" class (The one in Libgdx sucks).
 *  This is the abstract class GameState. Any given GameState should override all 6 of the current methods.
 */
public abstract class GameState {

    //The instance of the GameStateManager.
    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        init();
    }

    /**
     * This method is called when the GameState is first created.
     * This is essentially a secondary constructor to keep things more organized.
     */
    public abstract void init();

    /**
     * This method will be called in each render loop. This is what the GameState should be doing
     * in every iteration of the game loop in the background (changing variables, updating entities, etc.).
     *
     * @param dt: The time passed between each render.
     */
    public abstract void update(float dt);

    /**
     * This method will primarily handle all drawing to the given GameState. In other words, all drawing should
     * be handled HERE.
     */
    public abstract void draw();

    /**
     * This method uses the <Gdx.input></Gdx.input> methods in order to change specific inputs of the game.
     * Using this, you should be able to affect all actors on the stage.
     */
    public abstract void handleInput();

    /**
     * This is called when the GameState is disposed. This should be called whenever the GameState is switched.
     */
    public abstract void dispose();
}
