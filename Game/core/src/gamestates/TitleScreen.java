package gamestates;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.MainGame;

/**
 * Created by Hongyu Wang on 12/25/2015.
 */
public class TitleScreen implements Screen {
    private MainGame game;
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
    public static int WIDTH, HEIGHT;

    public TitleScreen(MainGame game){
        Gdx.graphics.setDisplayMode(1366, 768, false);
        init(game);

    }

    //Initializes all relevant attributes for the title screen.
    private void init(MainGame game){
        this.game = game;
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("title_font.fnt"));
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();
        gl = new GlyphLayout();
        TITLE = "Shield Lieutenant";
        current_selection = 0;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    public void draw() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        draw_title();
        draw_menu();
        handle_input();
        batch.end();
    }

    //Private method that draws the title. This draws it at 10% of the height.
    private void draw_title(){
        gl.setText(font, TITLE);
        font.getData().setScale(3f, 3f);
        font.setColor(1, 1, 1, 0);
        font.draw(
                batch,
                TITLE,
                (WIDTH - gl.width) / 2,
                HEIGHT * .1f
        );
    }

    /*Private method that draws the gamestate.menu. This also highlights the currently selected
    * option in red.
    */
    private void draw_menu(){
        for (int i = 0; i < 6; i ++){
            gl.setText(font, MENU_ITEMS[current_selection]);

            if (i == current_selection)
                font.setColor(1, 0, 0, 0);
            else
                font.setColor(1, 1, 1, 0);

            font.draw(
                    batch,
                    TITLE,
                    (WIDTH - gl.width)/2,
                    HEIGHT*.4f+i*.1f
            );
        }
    }

    //Private method handling keyboard input.
    private void handle_input(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            System.out.println("UP");
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            System.out.println("DOWN");
        }

    }



    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
