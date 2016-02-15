package gamestates.almanacState;

import entities.managers.GameStateManager;
import gamestates.GameState;
import gamestates.almanacState.components.Component;

/**
 * Created by Hongyu Wang on 1/25/2016.
 */
public class AlmanacState extends GameState {
    //This is the almanac directory file for the GameState class.
    private AlmanacPage [][] directory;
    public AlmanacState(GameStateManager gsm){
        super(gsm);

    }

    @Override
    public void init() {
        directory = new AlmanacPage[2][];

        //Weapons
        directory[0] = new AlmanacPage[3];

        //Enemies
        directory[1] = new AlmanacPage[8];

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void draw() {

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }


}
