package gamestates;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public GameStateManager(SpriteBatch sb){
        setState(PLAY);
        sprite_batch = sb;
    }

    /**
     * This change the current GameState into a new GameState.
     * @param state: int
     *              This is an integer representing the GameStates available.
     */
    public void setState(int state){
        if (gameState != null){
            gameState.dispose();
        }
        if (state == MENU){

        }
        if (state == PLAY){
            gameState = new Play(this);
        }
    }


    public void update(float dt){
        gameState.update(dt);
    }

    public void draw() {
        gameState.draw();
    }

    public void dispose(){
        gameState.dispose();
    }

    public SpriteBatch getSpriteBatch(){
        return sprite_batch;
    }
}
