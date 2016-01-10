package entities.managers;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.MainGame;
import gamestates.GameState;
import gamestates.menuState.Menu;
import gamestates.playState.Play;

/**
 * Created by Hongyu Wang on 12/27/2015.
 * The primary control class as to which state the game is currently in.
 *
 */
public class GameStateManager {

    // current game state
    private GameState gameState;
    private SpriteBatch sprite_batch;
    public static final int MENU = 0;
    public static final int PLAY = 1;
    private MainGame game;
    public GameStateManager(SpriteBatch sb, MainGame game){
        sprite_batch = sb;
        setState(MENU);
        this.game = game;

    }

    /**
     * This change the current GameState into a new GameState.
     * @param state: int
     *              This is an integer representing the GameStates available.
     */
    public void setState(int state){
        if (gameState != null){
            dispose();
        }
        if (state == MENU){
            gameState = new Menu(this);
        }
        if (state == PLAY){
            gameState = new Play(this);
        }
    }

    /**
     * Calls the update method of the specific game_state.
     * @param dt -  the default <float></float> value of
     *              the render loop.
     */
    public void update(float dt){
        gameState.update(dt);
    }

    /**
     * Calls the draw method of the specific game_state.
     */
    public void draw() {
        gameState.draw();
    }

    /**
     * Calls the dispose method of the specific game_state. This is called also when
     * switching states.
     */
    public void dispose(){
        gameState.dispose();
    }

    public SpriteBatch getSpriteBatch(){
        return sprite_batch;
    }

}
