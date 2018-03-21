package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ColorQ;

public class Bubble extends InteractiveTileObject{
    /**
     * Constructor
     * @param world
     * @param map
     * @param rect
     */
    public Bubble(World world, TiledMap map, Rectangle rect){
        super(world, map, rect);
        fixture.setUserData(this);
        //setCategoryFilter(ColorQ.BUBBLE_BIT);

    }

    /**
     * onHeadHit displays a collision message in the console
     * if the ship touches the bubble it is destroyed and a sound plays
     */
    @Override
    public void onHeadHit() {
        /*Gdx.app.log("Bubble", "Collision");
        setCategoryFilter(ColorQ.DESTROYED_BIT);
        ColorQ.manager.get("audio/pop.ogg", Sound.class).play();*/
    }
}