package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Hairuo on 2015-11-11.
 */
public class Test extends Actor{
    private Sprite sprite;
    public Test(){
        sprite = new Sprite(new Texture(("badlogic.jpg")));
        setBounds(sprite.getX(), sprite.getY(), sprite.getWidth(),sprite.getHeight());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
