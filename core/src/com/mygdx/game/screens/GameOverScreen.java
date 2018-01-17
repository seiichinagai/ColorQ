package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ColorQ;
import com.mygdx.game.sprites.ColorShip;

public class GameOverScreen implements Screen{
	private Viewport viewport;
	private Stage stage;
	
	private Game game;

	/**
	 * GameOverScreen occurs when the player crashes
	 * @param game
	 */

	public GameOverScreen(Game game){
		this.game = game;
		viewport = new FitViewport(ColorQ.V_WIDTH, ColorQ.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, ((ColorQ) game).batch);
		
		Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

		/*
		Game Over HUD
		 */
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		Label gameOverLabel = new Label("Game Over", font);
		Label playAgainLabel = new Label("Press any button to Play Again", font);
		
		table.add(gameOverLabel).expandX();
		table.row();
        table.add(playAgainLabel).expandX().padTop(10f);
		
		stage.addActor(table);
		
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// Reset Game
		if(Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            game.setScreen(new PlayScreen((ColorQ) game));
            ColorShip.setDead(false);
            dispose();
        }
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}

}
