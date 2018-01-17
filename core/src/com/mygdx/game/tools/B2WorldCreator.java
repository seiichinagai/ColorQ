package com.mygdx.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.sprites.BlueWall;
import com.mygdx.game.sprites.GrayWall;
import com.mygdx.game.sprites.RedWall;
import com.mygdx.game.sprites.YellowWall;

public class B2WorldCreator {
	public B2WorldCreator(World world, TiledMap map){
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		// create gray wall bodies/fixtures
		for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new GrayWall(world, map, rect);
		}
		
		// create red wall bodies/fixtures
		for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new RedWall(world, map, rect);
		}
		
		// create yellow wall bodies/fixtures
		for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new YellowWall(world, map, rect);
		}
		
		// create blue wall bodies/fixtures
		/*for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			new BlueWall(world, map, rect);
		}*/
	}
	
}
