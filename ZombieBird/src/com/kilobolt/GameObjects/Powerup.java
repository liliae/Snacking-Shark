package com.kilobolt.GameObjects;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Powerup extends Scrollable {

	public Circle p1 = new Circle(), p5 = new Circle();
	public Rectangle p2 = new Rectangle(), p3 = new Rectangle(), p4 = new Rectangle();
	
	private boolean isScored = false;
	private boolean hasRestarted = false;

	

	// When Pipe's constructor is invoked, invoke the super (Scrollable)
	// constructor
	public Powerup(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		if(position.y < 11)
			position.y = 50;
		
		// The set() method allows you to set the top left corner's x, y coordinates,
		// along with the width and height of the rectangle

		p1.set(position.x + (width/2), position.y + (height/2),  width/2);
		p2.set(position.x, position.y,  width, height);
		p3.set(position.x, position.y,  width, height);
		p4.set(position.x, position.y,  width, height);
		p5.set(position.x + (width/2), position.y + (height/2),  width/2);
		

	}

	@Override
	public void reset(float newX) {
		// Call the reset method in the superclass (Scrollable)
		super.reset(newX);
		isScored = false; // immediate death
		hasRestarted = false;
	}

	public void onRestart(float x, float scrollSpeed) {
		velocity.x = scrollSpeed;
		reset(x);
	}


	public boolean collides(Bird bird) {
		if (position.x < bird.getX()) {
			
			return (Intersector.overlaps(bird.getBoundingCircle(), p1) ||
					Intersector.overlaps(bird.getBoundingCircle(), p2) ||
					Intersector.overlaps(bird.getBoundingCircle(), p3) ||
					Intersector.overlaps(bird.getBoundingCircle(), p4) ||
					Intersector.overlaps(bird.getBoundingCircle(), p5));
			// Returns true of circle arg collides with rect arg
		}
		return false;
	}
	
	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}
	
	public boolean hasRestarted() {
		return hasRestarted;
	} 
	
	public void setReset(boolean b){
		hasRestarted = b;
	}

	public Circle getP1() {
		return p1;
	}

	public Rectangle getP2() {
		return p2;
	}

	public Rectangle getP3() {
		return p3;
	}

	public Rectangle getP4() {
		return p4;
	}

	public Circle getP5() {
		return p5;
	}
/*
	public Circle getBoundingCircle() {
		return boundingCircle;
	}*/

	
}
