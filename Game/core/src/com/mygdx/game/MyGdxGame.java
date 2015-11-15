package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;



public class MyGdxGame extends ApplicationAdapter {
	GameStage gStage;
	Test test;
	Player player;
	int bound_x;
	int bound_y;
	@Override
	public void create () { //initializes everything

		Gdx.graphics.setDisplayMode(1366, 768, false);
		player = new Player(1000,1000);
		test = new Test();
		ScreenViewport viewport = new ScreenViewport();
		gStage = new GameStage(viewport);

		Gdx.input.setInputProcessor(gStage);

		gStage.addActor(player);
		gStage.addActor(test);
		gStage.setKeyboardFocus(player);





	}



	public void updateCamera(Stage stage){ //locks the camera onto the player
		int xOffest = (int)player.getSprite().getWidth()/2;
		int yOffest = (int)player.getSprite().getHeight()/2;
		stage.getViewport().getCamera().position.set((int) player.getCoordX() + xOffest, (int) player.getCoordY() + yOffest, 0);
	}

	@Override
	public void render() { //renders stuff
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gStage.act(0.166f);
		gStage.draw();
		updateCamera(gStage);

	}

	@Override
	public void dispose() { // disposes stuff
		gStage.dispose();

	}

	public boolean CehckBounds(int xBound, int yBound){ //checks if the spaceship is within the set arena

		return true;

	}
}
