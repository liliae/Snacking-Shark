package com.kilobolt.GameWorld;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kilobolt.GameObjects.Bird;
import com.kilobolt.GameObjects.ScrollHandler;
import com.kilobolt.ZBHelpers.AssetLoader;

public class GameWorld {

	private Bird bird;
	private ScrollHandler scroller;
	public Rectangle ground, sky;
	
	private int score = 0;
	private float runTime = 0;
	public boolean mute = false;
	public boolean loggedIn = false;
	public int qns;
	
	private int midPointY, midPointX;
	private GameRenderer renderer;
	private Quiz quiz = new Quiz();
	
	private GameState currentState;
	

	public enum GameState {
		MENU, INSTRUCTIONS, SETTINGS,
		CREDITS,
		READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSE,
		QUIZ
	}
	/*
	 * MENU - On start; alter to include settings, instructions
	 * READY - prompt to start
	 * RUNNING - game execute
	 * GAMEOVER - zero life, offer retry onclick(button)
	 * HIGHSCORE - yeh
	 * PAUSE - onclick(pause)
	 */

	public GameWorld(int midPointX, int midPointY) {
		currentState = GameState.MENU; //opens menu
		this.midPointX = midPointX;
		this.midPointY = midPointY;
		bird = new Bird(midPointX, midPointY - 5, 17, 12);
		
		// The grass should start 66 pixels below the midPointY
		scroller = new ScrollHandler(this, 2*midPointY -20);
		ground = new Rectangle(0, 2*midPointY - 20, 2*midPointX, 30);
		sky = new Rectangle(0, 0, 2*midPointX, 30);
		
		AssetLoader.bgm.play();
		AssetLoader.bgm.setVolume(0.3f);
		
	}

	public void update(float delta) {
		runTime += delta;

		switch (currentState) {
		case READY:
			updateReady(delta);
			break;
			
		case MENU:
			updateReady(delta); //menu redirects to ready stage
			break;

		case RUNNING:
			updateRunning(delta);
			break;
			
		case PAUSE:
			break;
		
		case INSTRUCTIONS:
			break;
			
		case SETTINGS:
			break;
			
		case QUIZ:
			break;
			
		case CREDITS:
			break;
			
		default:
			break;
		}

	}

	

	private void updateReady(float delta) { //updates animation at ready stage
		bird.updateReady(runTime);
		scroller.updateReady(delta);
	}

	public void updateRunning(float delta) { //updates running stage
		if (delta > .15f) {
			delta = .15f;
		}

		bird.update(delta);
		scroller.update(delta);

		
		if (scroller.collides(bird) && bird.isAlive()) { //Set to have a return of true irregardless danger or powerup hits
            scroller.stop();
            bird.die();
            
            if(!mute){
            AssetLoader.dead.play();
            }
			renderer.prepareTransition(255, 255, 255, .3f);
            
            currentState = GameState.GAMEOVER;
            
            if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
            
        }


		if (Intersector.overlaps(bird.getBoundingCircle(), ground) || Intersector.overlaps(bird.getBoundingCircle(), sky)){

			if (bird.isAlive()) {
				
				if(!mute){
				AssetLoader.dead.play();}
				renderer.prepareTransition(255, 255, 255, .3f);

				bird.die();
			}

			scroller.stop();
			bird.decelerate();
			if(!mute){
			AssetLoader.dead.play();}
			
			currentState = GameState.GAMEOVER;

			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}
	}

	public Bird getBird() {
		return bird;

	}

	public int getMidPointY() {
		return midPointY;
	}
	
	public int getMidPointX(){
		return midPointX;
	}

	public ScrollHandler getScroller() {
		return scroller;
	}

	public int getScore() {
		return score;
	}

	public void addScore(int increment) {
		score += increment;
	}

	public void start() {
		currentState = GameState.RUNNING;
	}
	
	public void menu(){
		currentState = GameState.MENU;
		renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void menuRestart(){
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
		menu();
	}

	public void ready() {
		currentState = GameState.READY;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void restart() {
		score = 0;
		bird.onRestart(midPointY - 5);
		scroller.onRestart();
		ready();
	}
	
	public void paused(){
		currentState = GameState.PAUSE;
	}
	
	public void resume(){
		currentState = GameState.RUNNING;
	}
	
	public void settings() {
		currentState = GameState.SETTINGS;
		renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void instruct(){
		currentState = GameState.INSTRUCTIONS;
		renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void credits(){
		currentState = GameState.CREDITS;
		renderer.prepareTransition(0, 0, 0, 1f);
	}

	public void quiz(){
		currentState = GameState.QUIZ;
		Random random;
		
		do {
			random = new Random();
			qns = random.nextInt(200)+1;
			}
		while (quiz.doneQ[qns] == true);
		quiz.doneQ[qns] = true;
		
	}
	
	public boolean isReady() {
		return currentState == GameState.READY; //returns true if the gamestate is ready
	}

	public boolean isGameOver() {
		return currentState == GameState.GAMEOVER;
	}

	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}

	public boolean isMenu() {
		return currentState == GameState.MENU;
	}
	
	public boolean isSettings(){
		return currentState == GameState.SETTINGS;
	}

	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}
	
	public boolean isPaused() {
		return currentState == GameState.PAUSE;
	}
	
	public boolean isInstructions(){
		return currentState == GameState.INSTRUCTIONS;
	}
	
	public boolean isQuiz(){
		return currentState == GameState.QUIZ;
	}
	
	public boolean isCredits(){
		return currentState == GameState.CREDITS;
	}
	
	public void setRenderer(GameRenderer renderer) {
		this.renderer = renderer;
	}

		
}
