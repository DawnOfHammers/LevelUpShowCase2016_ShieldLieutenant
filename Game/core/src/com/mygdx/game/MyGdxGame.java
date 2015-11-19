package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ship.Player;


public class MyGdxGame extends Game {

	@Override
	public void create () { //initializes everything

		setScreen(new GameScreen(this));
	}




	@Override
	public void render() { //renders stuff
		super.render();


	}

	@Override
	public void dispose() { // disposes stuff


	}

}