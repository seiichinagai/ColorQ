package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ColorNode;
import com.mygdx.game.ColorQ;
import com.mygdx.game.ShipQueue;
import com.mygdx.game.screens.PlayScreen;

public class ColorShip extends Sprite{
	public enum State {DEAD};
	public State currentState;
	
	public World world;
	public Body b2body;
	private TextureRegion redShip;
	private TextureRegion yellowShip;
	private TextureRegion blueShip;
	
	private ColorNode redNode;
	private ColorNode yellowNode;
	private ColorNode blueNode;
	public static ShipQueue queue;
	
	private static boolean isDead = false;
	private static boolean wins = false;
	
	public ColorShip(World world, PlayScreen screen){
		redNode = new ColorNode(redShip, "RED");
		yellowNode = new ColorNode(yellowShip, "YELLOW");
		blueNode = new ColorNode(blueShip, "BLUE");
		
		queue = new ShipQueue(redNode);
		queue.add(yellowNode);
		queue.add(blueNode);
		
		this.world = world;
		
		
		defineShip();
		redNode.setElement(new TextureRegion(screen.getAtlas().findRegion("RedShip"), 0, 0, 16, 16));
		yellowNode.setElement(new TextureRegion(screen.getAtlas().findRegion("YellowShip"), 0, 0, 16, 16));
		blueNode.setElement(new TextureRegion(screen.getAtlas().findRegion("BlueShip"), 0, 0, 16, 16));
		setBounds(0, 0, 16 / ColorQ.PPM, 16 / ColorQ.PPM);
		setRegion(queue.getCurrent().getElement());
	}
	
	public void update(float dt){
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
		handleInput();
	}
	
	public void handleInput(){
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
			queue.next();
			setRegion(queue.getCurrent().getElement());
		}
	}
	
	public TextureRegion getFrame(float dt){
		return null;
	}
	
	public boolean isDead(){
		return isDead;
	}
	
	public boolean wins(){
		return wins;
	}
	
	public static void setWins(boolean winss){
		wins = winss;
	}
	
	public void defineShip(){
		BodyDef bdef = new BodyDef();
		bdef.position.set(100 / ColorQ.PPM, 100 / ColorQ.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / ColorQ.PPM);
		fdef.filter.categoryBits = ColorQ.SHIP_BIT;
		fdef.filter.maskBits = ColorQ.DEFAULT_BIT | ColorQ.RED_WALL_BIT | ColorQ.GRAY_WALL_BIT | ColorQ.YELLOW_WALL_BIT
				| ColorQ.BLUE_WALL_BIT;
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
		
		EdgeShape head = new EdgeShape();
		head.set(new Vector2(8 / ColorQ.PPM , 8 / ColorQ.PPM), new Vector2(8 / ColorQ.PPM , 8 / ColorQ.PPM));
		fdef.shape = head;
		fdef.isSensor = true;
		
		b2body.createFixture(fdef).setUserData("head");
	}
	
	
	public void hit(){
		setDead(true);
		
	}

	public static void setDead(boolean isDeadd) {
		isDead = isDeadd;
	}
}
