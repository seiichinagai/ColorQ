package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ColorQ;

public class GrayWall extends InteractiveTileObject{
	public GrayWall(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(ColorQ.GRAY_WALL_BIT);
		
	}

	@Override
	public void onHeadHit() {
		// TODO Auto-generated method stub
		Gdx.app.log("Gray Wall", "Collision");
		ColorShip.setDead(true);
	}
}
