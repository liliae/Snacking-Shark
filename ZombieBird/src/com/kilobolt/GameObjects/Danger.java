package com.kilobolt.GameObjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Danger extends Scrollable {

	/*
	 * Renamed as obstacles class, create new class for powerup, prompt question
	 * Change collison to separate entities of object from an array of texture
	 */

	private Rectangle d1 = new Rectangle(), d2 = new Rectangle(), d3 = new Rectangle(), d4 = new Rectangle(), d5 = new Rectangle(),d6=new Rectangle();

	private boolean isScored = false;


	public Danger(float x, float y, int width, int height, float scrollSpeed) { // constructor
		super(x, y, width, height, scrollSpeed);
	}

	@Override
	public void update(float delta) {
		// Call the update method in the superclass (Scrollable)
		super.update(delta);

		if (position.y < 11)
			position.y = 20;

		// The set() method allows you to set the top left corner's x, y
		// coordinates,
		// along with the width and height of the rectangle
		// Determines the collision of box


		/* d1 - coral 1
		 * d2 - coral 2
		 * d3 - coral 3
		 * d4 - plastic bag
		 * d5 - bottle
		 * d6 - trawler */
		d1.set(position.x, position.y, width, height);
		d2.set(position.x, position.y, width, height);
		d3.set(position.x, position.y, width, height);
		d4.set(position.x, position.y, width, height);
		d5.set(position.x, position.y, width, height);
		d6.set(position.x, position.y, width, height);
	}

	@Override
	public void reset(float newX) {
		
		super.reset(newX);
		isScored = false; // 0
	}

	public void onRestart(float x, float scrollspeed) {
		velocity.x = scrollspeed;
		reset(x);
	}

	public boolean collides(Bird bird) {

			return (Intersector.overlaps(bird.getBoundingCircle(), d1)
					|| Intersector.overlaps(bird.getBoundingCircle(), d2)
					|| Intersector.overlaps(bird.getBoundingCircle(), d3)
					|| Intersector.overlaps(bird.getBoundingCircle(), d4)
					|| Intersector.overlaps(bird.getBoundingCircle(), d5)
					|| Intersector.overlaps(bird.getBoundingCircle(), d6));

	}

	public boolean isScored() {
		return isScored;
	}

	public void setScored(boolean b) {
		isScored = b;
	}

	public Rectangle getD1() {
		return d1;
	}

	public Rectangle getD2() {
		return d2;
	}

	public Rectangle getD3() {
		return d3;
	}

	public Rectangle getD4() {
		return d4;
	}

	public Rectangle getD5() {
		return d5;
	}

	public Rectangle getD6() {
		return d6;
	}

	/*
	public Rectangle getBoundingRect() {
		return boundingRect;
	}*/
}
