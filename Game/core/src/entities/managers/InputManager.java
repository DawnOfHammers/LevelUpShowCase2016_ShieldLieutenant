package entities.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import java.util.Hashtable;

/**
 * Created by Hongyu Wang on 1/9/2016.
 * This class will control all inputs across all platforms.
 * The way the class is structured is through static final integer series.
 * Each series will represent a specific series of inputs.
 * These will then be placed within a private <Hashtable></Hashtable> data type.
 */
public class InputManager implements InputProcessor{
    //This array controls how many controls there are currently present in the game.
    //This should directly reflect the number of controls within each series that are present.
    //Maintaining accuracy here is essential for proper function of this class.
    private static final int [] number_of_controls = {7, 4, 1};

    //These inputs are for player control. These are the 100 series inputs.
    public static final int PLAYER_FORWARD = 100;
    public static final int PLAYER_LEFT = 101;
    public static final int PLAYER_RIGHT = 102;
    public static final int PLAYER_SHIELD_1_LEFT = 103;
    public static final int PLAYER_SHIELD_1_RIGHT = 104;
    public static final int PLAYER_SHIELD_2_LEFT = 105;
    public static final int PLAYER_SHIELD_2_RIGHT = 106;


    //These inputs are for menu control. These are the 200 series inputs.
    public static final int MENU_UP = 200;
    public static final int MENU_DOWN = 201;
    public static final int MENU_CONFIRM = 202;
    public static final int RETURN_TO_MENU = 203;

    //These inputs are for power_up control. These are the 300 series inputs.
    public static final int ACTIVATE_POWERUP_1 = 300;

    /*This is the main control centre of the game. All inputs are fed into the control table, and all inputs
    * gained from this control table. All actions to the control table should be accessed by 2 major public methods
    */
    private static Hashtable<Integer, Boolean> control_table;

    public InputManager(){
        init();
    }

    //Initializer method inside the InputManager class.
    private void init(){
        control_table = new Hashtable<Integer, Boolean>();
        for (int i = 0; i < number_of_controls.length; i++){
            for (int j = 0; j < number_of_controls[i]; j ++) {
                int key = (i + 1) * 100 + j;
                control_table.put(key, false);
            }
        }
    }

    /**
     * This method gets the state of a specific input.
     * The <key></key> parameter is a static final integer attribute located within the
     * <InputManager></InputManager> class.
     * @param key - an integer key accessible through static final integers in <InputManager></InputManager>
     * @return - the state of the key.
     */
    public static boolean get_input_state(int key){
        return control_table.get(key);
    }


    private int [] set_input_keyboard(int keycode){
        switch (keycode) {
            case Input.Keys.LEFT: return new int[]{PLAYER_LEFT};
            case Input.Keys.RIGHT: return new int[]{PLAYER_RIGHT};
            case Input.Keys.UP: return new int[]{PLAYER_FORWARD, MENU_UP};
            case Input.Keys.Q: return new int[]{InputManager.PLAYER_SHIELD_1_LEFT};
            case Input.Keys.W: return new int[]{InputManager.PLAYER_SHIELD_1_RIGHT};
            case Input.Keys.E: return new int[]{InputManager.PLAYER_SHIELD_2_LEFT};
            case Input.Keys.R: return new int[]{InputManager.PLAYER_SHIELD_2_RIGHT};
            case Input.Keys.ESCAPE: return new int[]{InputManager.RETURN_TO_MENU};
            case Input.Keys.DOWN: return new int[]{MENU_DOWN};
            case Input.Keys.ENTER: return new int []{MENU_CONFIRM};
            case Input.Keys.T: return new int[]{ACTIVATE_POWERUP_1};

        }
        return new int []{};
    }


    @Override
    public boolean keyDown(int keycode) {
        for (int i : set_input_keyboard(keycode)){
            control_table.put(i, true);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (int i : set_input_keyboard(keycode)){
            control_table.put(i, false);
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
