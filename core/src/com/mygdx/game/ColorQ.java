package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.PlayScreen;

public class ColorQ extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	public static final float PPM = 100;

	/*
	The following "BITS" are the 'categories' that the ship will accept as collisions
	 */
	public static final short DEFAULT_BIT = 1;
	public static final short SHIP_BIT = 2;
	public static final short RED_WALL_BIT = 4;
	public static final short GRAY_WALL_BIT = 8;
	public static final short YELLOW_WALL_BIT = 16;
	public static final short BLUE_WALL_BIT = 32;
	public static final short DESTROYED_BIT = 64;
	//public static final short BUBBLE_BIT = 128;

	public SpriteBatch batch;
	public static AssetManager manager;

	/**
	 * this method loads sounds, creates the game's sprite batch, and creates the PlayScreen
	 */
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/colorQ_music.ogg", Music.class);
		//manager.load("audio/pop.ogg", Sound.class);

		manager.finishLoading();
		
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render(){
		super.render();
	}
	
	@Override
	public void dispose () {
	}
}
