package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ship.Player;


public class MyGdxGame extends Game {
	GameScreen g_screen;

	@Override
	public void create () { //initializes everything
		g_screen = new GameScreen(this,1000,1000);
		setScreen(g_screen);
	}




	@Override
	public void render() { //renders stuff
		super.render();


	}

	@Override
	public void dispose() { // disposes stuff
		g_screen.dispose();

	}

}