package gamestates.almanacState;

import entities.managers.GameStateManager;
import gamestates.GameState;
import gamestates.almanacState.components.Component;

/**
 * Created by Hongyu Wang on 1/25/2016.
 */
public abstract class AlmanacState extends GameState {
    protected String file_name;
    protected Component [] asset;
    public AlmanacState(String file_name, GameStateManager gsm){
        super(gsm);
        this.file_name = file_name;
    }


}
