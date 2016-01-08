package gamestates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Hongyu Wang on 12/29/2015.
 */
public class TestActor extends Actor {
    public Sprite sprite;
    public TestActor(){
        sprite = new Sprite(new Texture("badlogic.jpg"));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) { //draws and moves the actor
        sprite.draw(batch);
    }
}
