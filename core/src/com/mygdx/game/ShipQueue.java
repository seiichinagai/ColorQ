package com.mygdx.game;


public class ShipQueue {
	private ColorNode head;
	private ColorNode current;
	private int count = 1;
	
	public ShipQueue(ColorNode head){
		this.head = head;
	}
	
	public void add(ColorNode node){
		if(count < 2){
			head.setNext(node);
			current = node;
			count++;
		}
		else{
			current.setNext(node);
			node.setNext(head);
			current = head;
			count = 1;
		}
	}
	
	
	
	public ColorNode next(){
		return current = current.getNext();
	}
	
	public ColorNode getCurrent(){
		return current;
	}
	
	public void setHead(ColorNode node){
		this.head = node;
	}
	
}

