package com.kilobolt.ZBHelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.kilobolt.GameObjects.Bird;
import com.kilobolt.GameObjects.ScrollHandler;
import com.kilobolt.GameWorld.GameWorld;
import com.kilobolt.GameWorld.Quiz;
import com.kilobolt.ZombieBird.ZBGame;
import com.kilobolt.ui.SimpleButton;

public class InputHandler implements InputProcessor {

	final ZBGame game;

	private Bird myBird;
	private GameWorld myWorld;
	private Quiz quiz = new Quiz();

	OrthographicCamera camera;
	public SpriteBatch batcher;

	/* Creates buttons & Handle interactions */
	private List<SimpleButton> menuButtons, pauseButtons, quizButtons, settingsButtons,
								muteButtons, unMuteButtons, logInButtons, logOutButtons,
								pauseUIButtons;

	private SimpleButton playButton, instructButton, setButton, pauseButton, choice1, choice2, choice3, muteButton,
			unmuteButton, loginButton, logoutButton, creditsButton, resumeButton, resetButton, backButton;

	private float scaleFactorX;
	private float scaleFactorY;

	float screenWidth = Gdx.graphics.getWidth();
	float screenHeight = Gdx.graphics.getHeight();

	int midPointX, midPointY;
	float WIDTH = Gdx.graphics.getWidth();
	float HEIGHT = Gdx.graphics.getHeight();
	float gameWidth = WIDTH / 2;
	float gameHeight = HEIGHT / (WIDTH / gameWidth);

	public InputHandler(GameWorld myWorld, float scaleFactorX, float scaleFactorY, ZBGame game) {
		this.game = game;
		// myBird now represents the gameWorld's bird
		this.myWorld = myWorld;
		myBird = myWorld.getBird();

		midPointX = myWorld.getMidPointX();
		midPointY = myWorld.getMidPointY();

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;

		menuButtons = new ArrayList<SimpleButton>();
		pauseButtons = new ArrayList<SimpleButton>();
		quizButtons = new ArrayList<SimpleButton>();
		settingsButtons = new ArrayList<SimpleButton>();
		pauseUIButtons = new ArrayList<SimpleButton>();
		muteButtons = new ArrayList<SimpleButton>();
		unMuteButtons = new ArrayList<SimpleButton>();
		logInButtons = new ArrayList<SimpleButton>();
		logOutButtons = new ArrayList<SimpleButton>();

		/* Menu UI */
		// Game Start
		playButton = new SimpleButton(midPointX - 50, midPointY + 20, 100, 20, AssetLoader.playButtonUp,
				AssetLoader.playButtonDown);
		menuButtons.add(playButton);

		// Instructions
		instructButton = new SimpleButton(midPointX - 50, (midPointY * 6 / 5) + 30, 100, 20,
				AssetLoader.instructButtonUp, AssetLoader.instructButtonDown);
		menuButtons.add(instructButton);

		// Setting
		setButton = new SimpleButton(midPointX - 50, (midPointY * 7 / 5) + 40, 100, 20, AssetLoader.settingsButtonUp,
				AssetLoader.settingsButtonDown);
		menuButtons.add(setButton);

		/* Setting UI */
		muteButton = new SimpleButton(midPointX - 50, midPointY * 4/5, 100, 20, AssetLoader.muteButtonUp,
				AssetLoader.muteButtonDown);
		muteButtons.add(muteButton);

		unmuteButton = new SimpleButton(midPointX - 50, midPointY * 4/5, 100, 20, AssetLoader.unmuteButtonUp,
				AssetLoader.unmuteButtonDown);
		unMuteButtons.add(unmuteButton);

		loginButton = new SimpleButton(midPointX - 50, midPointY, 100, 20, AssetLoader.loginButtonUp,
				AssetLoader.loginButtonDown);
		logInButtons.add(loginButton);

		logoutButton = new SimpleButton(midPointX - 50, midPointY, 100, 20, AssetLoader.logoutButtonUp,
				AssetLoader.logoutButtonDown);
		logOutButtons.add(logoutButton);

		creditsButton = new SimpleButton(midPointX - 50, midPointY * 6/5, 100, 20, AssetLoader.creditsButtonUp,
				AssetLoader.creditsButtonDown);
		settingsButtons.add(creditsButton);

		/* Pause UI */

		resumeButton = new SimpleButton(midPointX - 50, midPointY + 20, 100, 20, AssetLoader.resumeButtonUp,
				AssetLoader.resumeButtonDown);
		pauseUIButtons.add(resumeButton);

		resetButton = new SimpleButton(midPointX - 50, (midPointY * 6 / 5) + 30, 100, 20, AssetLoader.restartButtonUp,
				AssetLoader.restartButtonDown);
		pauseUIButtons.add(resetButton);

		backButton = new SimpleButton(midPointX - 50, (midPointY * 7 / 5) + 40, 100, 20, AssetLoader.backButtonUp,
				AssetLoader.backButtonDown);
		pauseUIButtons.add(backButton);

		/* Game Running State */
		// Pause Button
		pauseButton = new SimpleButton(0, 0, 10, 10, AssetLoader.pauseButtonUp, AssetLoader.pauseButtonDown);
		pauseButtons.add(pauseButton);

		// Quiz Button
		choice1 = new SimpleButton(midPointX - (AssetLoader.quizButtonUp.getRegionWidth() / 4), midPointY,
				gameWidth - 50, 40, AssetLoader.quizButtonUp, AssetLoader.quizButtonDown);
		quizButtons.add(choice1);

		choice2 = new SimpleButton(midPointX - (AssetLoader.quizButtonUp.getRegionWidth() / 4),
				(midPointY * 6 / 5) + 10, gameWidth - 50, 40, AssetLoader.quizButtonUp, AssetLoader.quizButtonDown);
		quizButtons.add(choice2);

		choice3 = new SimpleButton(midPointX - (AssetLoader.quizButtonUp.getRegionWidth() / 4),
				(midPointY * 7 / 5) + 20, gameWidth - 50, 40, AssetLoader.quizButtonUp, AssetLoader.quizButtonDown);
		quizButtons.add(choice3);

	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// occurs when button is pressed down (left click)
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		/*
		Vector3 touchpoint;
		Rectangle midBtm = new Rectangle(0, midPointY, screenWidth, screenHeight / 2);
		*/

		if (myWorld.isMenu()) {
			playButton.isTouchDown(screenX, screenY); // OR
			instructButton.isTouchDown(screenX, screenY); // OR
			setButton.isTouchDown(screenX, screenY);

		} else if (myWorld.isInstructions()) {
			if (Gdx.input.isTouched())
				myWorld.menu();

		} else if (myWorld.isReady()) {
			myWorld.start();

		} else if (myWorld.isRunning()) { // If desktop mode, disable this
			if (pauseButton.isTouchDown(screenX, screenY))
				myBird.onStop();
			
			/*
			  if (Gdx.input.isTouched()) { touchpoint = new
			  Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
			  
			  if (midBtm.contains(touchpoint.x, touchpoint.y)) {
			  myBird.onDown(); } else myBird.onUp(); }
			*/

		} else if (myWorld.isPaused()) {
			resumeButton.isTouchDown(screenX, screenY);
			resetButton.isTouchDown(screenX, screenY);
			backButton.isTouchDown(screenX, screenY);
		}
		if (myWorld.isQuiz()) {
			choice1.isTouchDown(screenX, screenY);
			choice2.isTouchDown(screenX, screenY);
			choice3.isTouchDown(screenX, screenY);
		}

		if (myWorld.isGameOver() || myWorld.isHighScore()) {
			myWorld.restart();
		}

		if (myWorld.isSettings()) {
			muteButton.isTouchDown(screenX, screenY);
			unmuteButton.isTouchDown(screenX, screenY);
			loginButton.isTouchDown(screenX, screenY);
			logoutButton.isTouchDown(screenX, screenY);
			creditsButton.isTouchDown(screenX, screenY);
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// occurs when button is released (left click)
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (myWorld.isMenu()) {
			if (playButton.isTouchUp(screenX, screenY)) {
				AssetLoader.coin.play();
				myWorld.ready();

			} else if (instructButton.isTouchUp(screenX, screenY)) {
				myWorld.instruct();
			}
			if (setButton.isTouchUp(screenX, screenY)) {
				myWorld.settings();
			}
		}
		if (myWorld.isSettings()) {
			if (myWorld.mute == false) {
				if (muteButton.isTouchUp(screenX, screenY)) {
					AssetLoader.bgm.stop();
					myWorld.mute = true;
				}
			} else if (myWorld.mute == true) {
				if (unmuteButton.isTouchUp(screenX, screenY)) {
					AssetLoader.bgm.play();
					myWorld.mute = false;
				}
			}

			if (myWorld.loggedIn == false) {
				loginButton.isTouchUp(screenX, screenY);
			} else if (myWorld.loggedIn == true) {
				logoutButton.isTouchUp(screenX, screenY);
			}

			if (creditsButton.isTouchUp(screenX, screenY)) {
				myWorld.credits();
			}
		}

		if (myWorld.isRunning()) {
			myBird.onStop();

			if (pauseButton.isTouchUp(screenX, screenY)) {
				myWorld.paused();
			}
		}
		if (myWorld.isPaused()) {
			if (resumeButton.isTouchUp(screenX, screenY)) {
				myWorld.resume();
			} else if (resetButton.isTouchUp(screenX, screenY)) {
				myWorld.restart();
			} else if (backButton.isTouchUp(screenX, screenY)) {
				myWorld.menuRestart();
			}
		}
		if (myWorld.isQuiz()) {

			int qns = myWorld.qns;
			if ((choice1.isTouchUp(screenX, screenY)) && (quiz.ansA[qns - 1] == 1)) {
				ScrollHandler.addScore(1);
				AssetLoader.coin.play();
				/* Tempt Scoring */
			} else if ((choice2.isTouchUp(screenX, screenY)) && (quiz.ansB[qns - 1] == 1)) {
				ScrollHandler.addScore(1);
				AssetLoader.coin.play();
				/* Tempt Scoring */
			} else if ((choice3.isTouchUp(screenX, screenY)) && (quiz.ansC[qns - 1] == 1)) {
				ScrollHandler.addScore(1);
				AssetLoader.coin.play();
				/* Tempt Scoring */
			}

			myWorld.resume();

		}
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		/*-------------------------------------
		 * Debugging Codes for lack of buttons |
		 * ------------------------------------
		 * H - INSTRUCTION MENU 
		 * ESCAPE - BACK TO MAIN MENU 
		 * UP - DIRECTIONAL UP 
		 * DOWN - DIRECTIONAL DOWN 
		 * SPACE - SELECT
		 * P - PAUSE STATE
		 * R - RESET GAME STAGE
		 * M - TOGGLE MUTE
		 * B - BACK
		 * -------------------------------------
		 */
		switch (keycode) {

		case Keys.SPACE:
			if (myWorld.isMenu()) {
				myWorld.ready();
			} else if (myWorld.isReady()) {
				myWorld.start();
			}
			if (myWorld.isGameOver() || myWorld.isHighScore()) {
				myWorld.restart();
			}
			break;

		case Keys.H:
			if (myWorld.isMenu()) {
				myWorld.instruct();
			} else if (myWorld.isInstructions()) {
				myWorld.menu();
			}
			if (myWorld.isPaused()) {
				myWorld.menuRestart();
			}
			break;

		case Keys.B:
			if(myWorld.isCredits()){
				myWorld.settings();
			}
			else if(myWorld.isInstructions() || myWorld.isSettings()){
				myWorld.menu();
			}

			break;
			
		case Keys.UP:
			myBird.onUp();
			break;

		case Keys.DOWN:
			myBird.onDown();
			break;

		case Keys.ESCAPE:
			myWorld.menu();
			break;

		case Keys.R:
			myWorld.restart();
			break;

		case Keys.P:
			if (myWorld.isRunning()) {
				myWorld.paused();
			} else if (myWorld.isPaused()) {
				myWorld.resume();
			}
			break;

		case Keys.M:
			if (myWorld.mute) {
				AssetLoader.bgm.play();
				myWorld.mute = false;
			} else if (!myWorld.mute) {
				AssetLoader.bgm.stop();
				myWorld.mute = true;
			}
			break;

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		if (keycode == Keys.UP) {
			myBird.onStop();
		}

		if (keycode == Keys.DOWN)
			myBird.onStop();

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

	public List<SimpleButton> getMenuButtons() {
		return menuButtons;
	}

	public List<SimpleButton> getPauseButtons() {
		return pauseButtons;
	}

	public List<SimpleButton> getQuizButtons() {
		return quizButtons;
	}

	public List<SimpleButton> getPauseUIButtons() {
		return pauseUIButtons;
	}

	public List<SimpleButton> getSettingsButtons() {
		return settingsButtons;
	}

	public List<SimpleButton> getMuteButtons() {
		return muteButtons;
	}
	
	public List<SimpleButton> getUnMuteButtons() {
		return unMuteButtons;
	}
	
	public List<SimpleButton> getLogInButtons() {
		return logInButtons;
	}
	public List<SimpleButton> getLogOutButtons() {
		return logOutButtons;
	}
}
