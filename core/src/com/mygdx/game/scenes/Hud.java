package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ColorQ;

public class Hud implements Disposable{
	public Stage stage;
	private Viewport viewport;
	
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	
	Label scoreLabel;
	Label levelLabel;
	Label worldLabel;
	Label pointsLabel;
	Label colorLabel[] = new Label[3];
	Label changeColorLabel;

	/**
	 * Hud contains the visual labels like level, points, time, etc.
	 * @param sb
	 */
	public Hud(SpriteBatch sb){
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(ColorQ.V_WIDTH, ColorQ.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		Table tableBottom = new Table();
		tableBottom.bottom();
		tableBottom.setFillParent(true);
		
		//scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		//levelLabel = new Label("1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		worldLabel = new Label("Level 1", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		//pointsLabel = new Label(" ", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
		changeColorLabel = new Label("Press SPACEBAR to change color", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
		
		colorLabel[0] = new Label("R ", new Label.LabelStyle(new BitmapFont(), Color.RED));
		colorLabel[1] = new Label("Y ", new Label.LabelStyle(new BitmapFont(), Color.YELLOW));
		colorLabel[2] = new Label("B", new Label.LabelStyle(new BitmapFont(), Color.BLUE));
		
		table.add(worldLabel).expandX().padTop(10);
		
		//for(Label l : colorLabel)
		//table.add(l).expandX().padTop(10);
		
		//tableBottom.add(changeColorLabel);
		
		//table.add(pointsLabel).expandX().padTop(10);
		//table.row();
		//table.add(levelLabel).expandX();
		//table.add(scoreLabel).expandX();
		
		stage.addActor(table);
		stage.addActor(tableBottom);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		stage.dispose();
	}
	
	
}
