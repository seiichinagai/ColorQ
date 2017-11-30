package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ColorNode {
	private ColorNode next;
	private TextureRegion element;
	private String color;
	
	public ColorNode(TextureRegion elem, String color){
		next = null;
		element = elem;
		this.color = color;
	}
	
	public ColorNode getNext(){
		return next;
	}
	
	public String getColor(){
		return color;
	}
	
	public void setNext(ColorNode node){
		next = node;
	}
	
	public TextureRegion getElement(){
		return element;
	}

	public void setElement(TextureRegion textureRegion) {
		this.element = textureRegion;
	}
}
