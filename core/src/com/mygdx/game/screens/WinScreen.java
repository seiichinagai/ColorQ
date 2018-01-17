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

public class WinScreen implements Screen{
	private Viewport viewport;
	private Stage stage;

	private Game game;

	/**
	 * WinScreen appears when the player completes the level, letting them know that they won
	 * @param game
	 */
	public WinScreen(Game game){
		this.game = game;
		viewport = new FitViewport(ColorQ.V_WIDTH, ColorQ.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, ((ColorQ) game).batch);

		// Win Screen HUD
		Label.LabelStyle font1 = new Label.LabelStyle(new BitmapFont(), Color.BLACK);
		Label.LabelStyle fontR = new Label.LabelStyle(new BitmapFont(), Color.RED);
		Label.LabelStyle fontY = new Label.LabelStyle(new BitmapFont(), Color.YELLOW);
		Label.LabelStyle fontB = new Label.LabelStyle(new BitmapFont(), Color.BLUE);
		
		Table table = new Table();
		table.center();
		table.setFillParent(true);
		
		Label gameOverLabel[] = new Label[7];
		
		gameOverLabel[0] = new Label("Y", fontR);
		gameOverLabel[1] = new Label("O", fontY);
		gameOverLabel[2] = new Label("U ", fontB);
		gameOverLabel[3] = new Label("W", fontR);
		gameOverLabel[4] = new Label("I", fontY);
		gameOverLabel[5] = new Label("N", fontB);
		gameOverLabel[6] = new Label("!", fontR);
		
		
		Label playAgainLabel = new Label("Press any button to Play Again", font1);
		
		for(Label l : gameOverLabel){
			table.add(l).expandX();
		}
		//table.add(gameOverLabel).expandX();
		table.row();
        //table.add(playAgainLabel).expandX().padTop(10f);
		
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
            ColorShip.setWins(false);
            dispose();
        }
		
		Gdx.gl.glClearColor(255, 255, 255, 1);
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
