package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
	private Stage stage;
	private Skin skin;
	private SpriteBatch batch;
	
	private TmxMapLoader mapLoader;
	private TiledMap map;
	private MapProperties prop;
	private OrthogonalTiledMapRenderer renderer;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private ColorShip player;
	
	private Music music;
	private Sound pop;

	/**
	 * PlayScreen is the screen where the user interacts with the game
	 * @param game
	 */
	public PlayScreen(ColorQ game){
		atlas = new TextureAtlas("ColorShips.pack");
		
		
		this.game = game;
		gamecam = new OrthographicCamera();
		gamePort = new FitViewport(ColorQ.V_WIDTH / ColorQ.PPM, ColorQ.V_HEIGHT / ColorQ.PPM, gamecam);
		hud = new Hud(game.batch);

		// Tiled Map
		mapLoader = new TmxMapLoader();
		map = mapLoader.load("Level1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1 / ColorQ.PPM);
		gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		// World
		world = new World(new Vector2(0, 0), true);
		player = new ColorShip(world, this);
		b2dr = new Box2DDebugRenderer();
		
		new B2WorldCreator(world, map);
		
		world.setContactListener(new WorldContactListener());

		// Sounds
		pop = ColorQ.manager.get("audio/pop.ogg", Sound.class);

		music = ColorQ.manager.get("audio/colorQ_music.ogg", Music.class);
        music.setLooping(true);
        music.setVolume(0.5f);
        music.play();

        batch = new SpriteBatch();

        // Slider
        Skin skin = new Skin(Gdx.files.internal("data/skin/cloud-form-ui.json"));
        stage = new Stage();
        Slider slider = new Slider(0, 100, 1, true, skin);
        slider.setWidth(20f);
        slider.setHeight(475f);
		slider.setPosition(50, 240, 0);

		//stage.addActor(slider);

		//Gdx.input.setInputProcessor(stage);
	}
	
	public TextureAtlas getAtlas(){
		return atlas;
	}

	@Override
	public void show() {
	}

	/**
	 * handleInput takes in user input (up or down arrow) and moves the ColorShip in the corresponding direction
	 * @param dt
	 */
	public void handleInput(float dt){
		if(Gdx.input.isKeyPressed(Input.Keys.UP))
			player.b2body.applyLinearImpulse(new Vector2(0, 0.075f), player.b2body.getWorldCenter(), true);
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
			player.b2body.applyLinearImpulse(new Vector2(0, -0.075f), player.b2body.getWorldCenter(), true);
	}

	/**
	 * The update method updates the game based on the ship's position
	 * @param dt
	 */
	public void update(float dt){
		handleInput(dt);
		// Give ship initial positive X velocity
		if(player.b2body.getLinearVelocity().x <= 1.5)
			player.b2body.applyLinearImpulse(new Vector2(0.1f, 0),  player.b2body.getWorldCenter(),  true);
		// sets ColorShip.isDead to true if touches ceiling
		if(player.b2body.getPosition().y >= ColorQ.V_HEIGHT / ColorQ.PPM)
			player.setDead(true);
		// sets ColorShip.isDead to true if touches ground
		if(player.b2body.getPosition().y <= -(ColorQ.V_HEIGHT / ColorQ.PPM / 16))
			player.setDead(true);
		// sets ColorShip.wins to true if reaches end of level
		if(player.getX() >= 38)
			player.setWins(true);

		world.step(1/60f, 6,  2);

		player.update(dt);
		
		gamecam.position.x = player.b2body.getPosition().x + 1;
		
		gamecam.update();
		renderer.setView(gamecam);
		
	}

	/**
	 *
	 * @param delta
	 */
	@Override
	public void render(float delta) {
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

		batch.begin();
		stage.draw();
		batch.end();

		// Instantiates GameOverScreen
		if(gameOver()){
			game.setScreen(new GameOverScreen(game));
			dispose();
		}
		// Instantiates WinScreen
		if(gameWin()){
			game.setScreen(new WinScreen(game));
			dispose();
		}
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

	@Override
	public void resize(int width, int height) {
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
}
