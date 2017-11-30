package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ColorQ;

public class YellowWall extends InteractiveTileObject{
	public YellowWall(World world, TiledMap map, Rectangle bounds){
		super(world, map, bounds);
		fixture.setUserData(this);
		setCategoryFilter(ColorQ.YELLOW_WALL_BIT);
		
	}

	@Override
	public void onHeadHit() {
		Gdx.app.log("Yellow Wall", "Collision");
		if(ColorShip.queue.getCurrent().getColor().equals("YELLOW"))
			setCategoryFilter(ColorQ.DESTROYED_BIT);
		else
			ColorShip.setDead(true);
	}
}
