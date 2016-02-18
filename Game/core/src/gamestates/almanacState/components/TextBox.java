package gamestates.almanacState.components;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is the first created component: a text box. This box contains a variety of
 * statistics regarding components.
 * Created by Hongyu Wang on 2/17/2016.
 */
public class TextBox extends Component{
    private String intializedText;
    /**
     * This constructor will create text from a designated file referenced
     * by the <path></path> variable.
     *
     * @param x      The x-coordinate of the component.
     * @param y      The y-coordinate of the component.
     * @param width  The width of the component.
     * @param height The height of the component.
     * @param path   The path of the file for the required text
     */
    public TextBox(int x, int y, int width, int height, String path) {
        super(x, y, width, height, null);
    }





    @Override
    protected void init() {

    }

    @Override
    protected void draw() {

    }

    @Override
    protected void doPressLogic() {

    }

    //TODO Create this initialization.
    private void intializeTextFromeFile(){

    }
}
