package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ColorQ;

public class RedWall extends InteractiveTileObject{
	/**
	 * Constructor
	 * @param world
	 * @param map
	 * @param bounds
	 */
	public RedWall(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(ColorQ.RED_WALL_BIT);
	}

	/**
	 * onHeadHit displays a collision message in the console
	 * if the ship color matches the Tiled Map Object color, destroy the object
	 * otherwise the player is killed
	 */
	@Override
	public void onHeadHit() {
		Gdx.app.log("Red Wall", "Collision");
		if(ColorShip.queue.getCurrent().getColor().equals("RED"))
			setCategoryFilter(ColorQ.DESTROYED_BIT);
		else
			ColorShip.setDead(true);
	}
}
