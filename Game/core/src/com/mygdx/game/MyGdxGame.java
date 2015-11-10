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
	World world;
	Player player;
	float accumulator = 0;
	Body playerHitBox;
	Box2DDebugRenderer debugRenderer;
	@Override
	public void create () {

		Gdx.graphics.setDisplayMode(1366, 768, false);
		player = new Player();
		ScreenViewport viewport = new ScreenViewport();
		gStage = new GameStage(viewport);

		Gdx.input.setInputProcessor(gStage);

		gStage.addActor(player);
		gStage.setKeyboardFocus(player);

		//world =  new World(new Vector2(0,0), true);
		//Box2DDebugRenderer debugRenderer = new Box2DDebugRenderer();




	}





	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		gStage.act(0.166f);
		gStage.draw();

	}

	@Override
	public void dispose() {
		world.dispose();
		debugRenderer.dispose();
		gStage.dispose();

	}
}
