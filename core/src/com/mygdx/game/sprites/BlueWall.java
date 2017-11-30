package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ColorQ;
import com.mygdx.game.sprites.ColorShip;

public class BlueWall extends InteractiveTileObject{
	public BlueWall(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(ColorQ.RED_WALL_BIT);
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Blue Wall", "Collision");
		if(ColorShip.queue.getCurrent().getColor().equals("BLUE"))
			setCategoryFilter(ColorQ.DESTROYED_BIT);
		else
			ColorShip.setDead(true);
	}
}
