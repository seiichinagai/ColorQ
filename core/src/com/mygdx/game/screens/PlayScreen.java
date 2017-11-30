package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.ColorQ;
import com.mygdx.game.scenes.Hud;
import com.mygdx.game.sprites.ColorShip;
import com.mygdx.game.tools.B2WorldCreator;
import com.mygdx.game.tools.WorldContactListener;


public class PlayScreen implements Screen{
	private ColorQ game;
	private TextureAtlas atlas;
	
	private OrthographicCamera gamecam;
	private Viewport gamePort;
	private Hud hud;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private MapProperties prop;
	private OrthogonalTiledMapRenderer renderer;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private ColorShip player;
	
	private Music music;
	
	public PlayScreen(ColorQ game){
		atlas = new TextureAtlas("ColorShips.pack");
		
		
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(ColorQ.V_WIDTH / ColorQ.PPM, ColorQ.V_HEIGHT / ColorQ.PPM, gamecam);
		hud = new Hud(game.batch);
		
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("Level1.tmx");
		prop = map.getProperties();
		renderer = new OrthogonalTiledMapRenderer(map, 1 / ColorQ.PPM);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		world = new World(new Vector2(0, 0), true);
		player = new ColorShip(world, this);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		world.setContactListener(new WorldContactListener());
		
		music = ColorQ.manager.get("audio/colorQ_music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput(float dt){
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			player.b2body.applyLinearImpulse(new Vector2(0, 0.075f), player.b2body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			player.b2body.applyLinearImpulse(new Vector2(0, -0.075f), player.b2body.getWorldCenter(), true);
		
		
	
	}
	
	public void update(float dt){
		handleInput(dt);
		if(player.b2body.getLinearVelocity().x <= 1.5)
			player.b2body.applyLinearImpulse(new Vector2(0.1f, 0),  player.b2body.getWorldCenter(),  true);
		if(player.b2body.getPosition().y >= ColorQ.V_HEIGHT / ColorQ.PPM)
			ColorShip.setDead(true);
		if(player.b2body.getPosition().y <= -(ColorQ.V_HEIGHT / ColorQ.PPM / 16))
			ColorShip.setDead(true);
		if(player.getX() >= 38)
			ColorShip.setWins(true);
		
		world.step(1/60f, 6,  2);

		player.update(dt);
		
		gamecam.position.x = player.b2body.getPosition().x + 1;
		
		gamecam.update();
		renderer.setView(gamecam);
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update(delta);
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
		
		b2dr.render(world, gamecam.combined);
		
		game.batch.setProjectionMatrix(gamecam.combined);
		game.batch.begin();
		player.draw(game.batch);
		game.batch.end();
		
		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
		if(gameOver()){
			game.setScreen(new GameOverScreen(game));
			dispose();
		}
		
		if(gameWin()){
			game.setScreen(new WinScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		gamePort.update(width, height);
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
		map.dispose();
		renderer.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
		
	}
	
	public boolean gameOver(){
		if(player.isDead() == true)
			return true;
		return false;
	}

	public boolean gameWin(){
		if(player.wins() == true)
			return true;
		return false;
	}
}
