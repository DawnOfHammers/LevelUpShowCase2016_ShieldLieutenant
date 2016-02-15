package gamestates.menuState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import entities.managers.InputManager;
import game.MainGame;
import gamestates.GameState;
import entities.managers.GameStateManager;



/**
 * Created by Hongyu Wang on 12/29/2015.
 */
public class Menu extends GameState {
    private SpriteBatch batch;
    private BitmapFont font;
    private GlyphLayout gl;
    private String TITLE;
    private int current_selection;
    private static final String [] MENU_ITEMS = {
            "Play",
            "Instructions",
            "Settings",
            "High Scores",
            "Almanac",
            "Exit"
    };

    public Menu(GameStateManager gsm) {
        super(gsm);

    }

    @Override
    public void init() {
        batch = gsm.getSpriteBatch();

        font = new BitmapFont(Gdx.files.internal("title_font.fnt"));
        gl = new GlyphLayout();
        TITLE = "Shield Lieutenant";
        current_selection = 0;
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        draw_title();
        draw_menu();

        batch.end();
    }

    //Private method that draws the title. This draws it at 10% of the height.
    private void draw_title(){
        font.getData().setScale(2f, 2f);
        gl.setText(font, TITLE);

        font.setColor(Color.WHITE);
        font.draw(
                batch,
                TITLE,
                (MainGame.WIDTH - gl.width) / 2,
                MainGame.HEIGHT * .9F
        );
    }

    /*Private method that draws the menu. This also highlights the currently selected
    * option in red.
    */
    private void draw_menu(){
        font.getData().setScale(1, 1);
        for (int i = 0; i < 6; i++) {
            gl.setText(font, MENU_ITEMS[i]);

            if (i == current_selection)
                font.setColor(Color.RED);
            else
                font.setColor(Color.WHITE);

            font.draw(
                    batch,
                    MENU_ITEMS[i],
                    (MainGame.WIDTH - gl.width)/2,
                    MainGame.HEIGHT*.5f - i*MainGame.HEIGHT*.05f
            );
        }
    }

    @Override
    public void handleInput() {
        if (InputManager.get_input_state(InputManager.MENU_UP)){
            current_selection = Math.max(0, current_selection - 1);
        }

        if (InputManager.get_input_state(InputManager.MENU_DOWN)){
            if (current_selection + 1 < MENU_ITEMS.length)
                current_selection++;
        }

        if (InputManager.get_input_state(InputManager.MENU_CONFIRM)){
            handleStateSwitch();
        }
    }

    private void handleStateSwitch(){
        switch (current_selection){
            case 0: gsm.setState(GameStateManager.PLAY);
                break;
            case 5: dispose();
                Gdx.app.exit();
                break;
        }

    }

    @Override
    public void dispose() {

    }

    @Override
    public void pause() {
        System.out.println("IDK WHY YOU'RE CALLING THIS METHOD");
    }

    @Override
    public void resume() {
        System.out.println("EYLMAOBOYS");
    }


}
