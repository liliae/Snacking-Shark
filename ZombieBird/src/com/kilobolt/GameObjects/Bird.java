package com.kilobolt.GameObjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.kilobolt.ZBHelpers.AssetLoader;

public class Bird {

	private Vector2 position;
	private Vector2 velocity;
	private Vector2 acceleration;

	private float rotation;
	private int width;
	private float height;

	private float originalY;

	private boolean isAlive;

	public Circle boundingCircle = new Circle(); // Handles Collision

	public Bird(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		
		position = new Vector2(x, y);
		velocity = new Vector2(0, 0);
		acceleration = new Vector2(0, 0); // 460 handles gravity
		
		
		isAlive = true; // Handles life state
	}

	public void update(float delta) {

		velocity.add(acceleration.cpy().scl(delta));

		if (velocity.y > 200) {
			velocity.y = 200;
		}

		// CEILING CHECK
		if (position.y < -20) {
			position.y = -20;
			velocity.y = 0;
		}

		position.add(velocity.cpy().scl(delta));

		boundingCircle.set(position.x + 30, position.y + 10, 12);

		// Rotate counterclockwise
		if (velocity.y < 0) {
			rotation -= 600 * delta;

			if (rotation < -10) {
				rotation = -10;
			}
		}

		// Rotate clockwise
		//if (isFalling() || !isAlive) {
		if(velocity.y > 0){
			rotation += 480 * delta;
			if (rotation > 0) {
				rotation = 10;
			}

		}

	}

	public void updateReady(float runTime) {
		position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
	}


	public boolean shouldntFlap() {
		return !isAlive;
	}

	public void onUp() { //should alter to key change soon
		if (isAlive) {
			AssetLoader.flap.play();
			velocity.y -= 50;	
		
		}
	}
	
	public void onDown() { //should alter to key change soon
		if (isAlive) {
			AssetLoader.flap.play();
			velocity.y += 50;	
		
		}
	}
	
	public void onStop(){
		if(isAlive){
			AssetLoader.flap.play();
			velocity.y = 0;
			rotation = 0;
		}
	}

	public void die() {
		isAlive = false;
		velocity.y = 0;
		
	}

	public void decelerate() {
		//stop bird from accelerating downwards once dead
		acceleration.y = 0;
	}

	public void onRestart(int y) {
		rotation = 0;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 0;
		isAlive = true;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getRotation() {
		return rotation;
	}

	public Circle getBoundingCircle() {
		return boundingCircle;
	}

	public boolean isAlive() {
		return isAlive;
	}

}
