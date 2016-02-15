package game;

import gamestates.playState.GameStage;

/**
 * Created by Hairuo on 2016-02-01.
 */
public class Spawner{
    GameStage gameStage;
    double level;
    double t_level;
    double unit_cap;
    public Spawner(GameStage stage, double level, double t_level, double unit_cap){
        this.gameStage = stage;
        this.level = level;
        this.t_level = level;
        this.unit_cap = unit_cap;

    }

    public void spawn(){
        
    }
}
