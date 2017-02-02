package com.kilobolt.ZBHelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

	public static Texture texture, logoTexture, DangerTexture, PowerupTexture, ssLogoTexture,
						sharkTexture, bgTexture, sbTexture, pauseTexture, buttonTexture, pauseMenuTexture,
						instructTexture, quizTexture, qnsTexture;
	public static TextureRegion logo, bg, grass, bird, birdDown, shark, birdUp, 
			playButtonUp, playButtonDown, instructButtonUp, instructButtonDown, settingsButtonUp, settingsButtonDown,
			pauseButtonUp, pauseButtonDown, quizButtonUp, quizButtonDown,
			muteButtonUp, muteButtonDown, loginButtonUp, loginButtonDown, creditsButtonUp, creditsButtonDown,
			logoutButtonUp, logoutButtonDown, unmuteButtonUp, unmuteButtonDown, restartButtonUp, restartButtonDown,
			resumeButtonUp, resumeButtonDown, backButtonUp, backButtonDown,
			instruct, question, ssLogo, ntuLogo, pauseMenu,
			ready, gameOver, highScore, scoreboard, star, noStar, retry;
	
	public static TextureRegion dd1, dd2, dd3, dd4, dd5, dd6,
								pp1, pp2, pp3, pp4, pp5;
	
	public static Animation birdAnimation;
	public static Sound dead, flap, coin;
	public static Music bgm;
	public static BitmapFont font, shadow, whiteFont, scoreFont, quizFont, quizFont1, quizFont2, credits;
	private static Preferences prefs;

	public static void load() {

		/* Uploading Data*/
		logoTexture = new Texture(Gdx.files.internal("data/ntuLogo.png"));
		logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		ssLogoTexture = new Texture(Gdx.files.internal("data/ssLogo.png"));
		ssLogoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		texture = new Texture(Gdx.files.internal("data/texture.png"));
		texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		sharkTexture = new Texture(Gdx.files.internal("data/shark.png"));
		sharkTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

		DangerTexture = new Texture(Gdx.files.internal("data/danger.png"));
		DangerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		PowerupTexture = new Texture(Gdx.files.internal("data/powerup.png"));
		PowerupTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		bgTexture = new Texture(Gdx.files.internal("data/bg.png"));
		bgTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sbTexture = new Texture(Gdx.files.internal("data/sandbed.png"));
		sbTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		instructTexture = new Texture(Gdx.files.internal("data/instruct.png"));
		instructTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		pauseTexture = new Texture(Gdx.files.internal("data/pause.png"));
		pauseTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		quizTexture = new Texture(Gdx.files.internal("data/quizTexture.png"));
		quizTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		buttonTexture = new Texture(Gdx.files.internal("data/buttonTexture.png"));
		buttonTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		pauseMenuTexture = new Texture(Gdx.files.internal("data/pauseBg.png"));
		pauseMenuTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		
		/* Attain texture data*/
		
		/* Buttons */
		playButtonUp = new TextureRegion(buttonTexture, 0, 0, 163, 32);
		playButtonDown = new TextureRegion(buttonTexture, 0, 32, 163, 32);
		playButtonUp.flip(false, true);
		playButtonDown.flip(false, true);
		
		instructButtonUp = new TextureRegion(buttonTexture,0,64,163,32);
		instructButtonDown = new TextureRegion(buttonTexture,0,96,163,32);
		instructButtonUp.flip(false, true);
		instructButtonDown.flip(false, true);
		
		settingsButtonUp = new TextureRegion(buttonTexture,0,128,163,32);
		settingsButtonDown = new TextureRegion(buttonTexture,0,160,163,32);
		settingsButtonUp.flip(false, true);
		settingsButtonDown.flip(false, true);
		
		muteButtonUp = new TextureRegion(buttonTexture,163,0,163,32);
		muteButtonDown = new TextureRegion(buttonTexture,163,32,163,32);
		muteButtonUp.flip(false, true);
		muteButtonDown.flip(false, true);
		
		unmuteButtonUp = new TextureRegion(buttonTexture,163,64,163,32);
		unmuteButtonDown = new TextureRegion(buttonTexture,163,96,163,32);
		unmuteButtonUp.flip(false, true);
		unmuteButtonDown.flip(false, true);
		
		creditsButtonUp = new TextureRegion(buttonTexture,163,128,163,32);
		creditsButtonDown = new TextureRegion(buttonTexture,163,160,163,32);
		creditsButtonUp.flip(false, true);
		creditsButtonDown.flip(false, true);
		
		loginButtonUp = new TextureRegion(buttonTexture,326,0,163,32);
		loginButtonDown = new TextureRegion(buttonTexture,326,32,163,32);
		loginButtonUp.flip(false, true);
		loginButtonDown.flip(false, true);
		
		logoutButtonUp = new TextureRegion(buttonTexture,326,32,163,32);
		logoutButtonDown = new TextureRegion(buttonTexture,326,64,163,32);
		logoutButtonUp.flip(false, true);
		logoutButtonDown.flip(false, true);
		
		resumeButtonUp = new TextureRegion(buttonTexture, 489,0,163,32);
		resumeButtonDown = new TextureRegion(buttonTexture,489,32,163,32);
		resumeButtonUp.flip(false, true);
		resumeButtonDown.flip(false, true);
		
		restartButtonUp = new TextureRegion(buttonTexture, 489, 64, 163, 32);
		restartButtonDown = new TextureRegion(buttonTexture,489,96,163,32);
		restartButtonUp.flip(false, true);
		restartButtonDown.flip(false, true);
		
		backButtonUp = new TextureRegion(buttonTexture,489,128,163,32);
		backButtonDown = new TextureRegion(buttonTexture, 489,160,163,32);
		backButtonUp.flip(false, true);
		backButtonDown.flip(false, true);

		pauseButtonUp = new TextureRegion(pauseTexture,0,0,10,10);
		pauseButtonDown = new TextureRegion(pauseTexture, 10, 0, 10, 10);
		pauseButtonUp.flip(false, true);
		pauseButtonDown.flip(false, true);
		
		quizButtonUp = new TextureRegion(quizTexture, 660, 0, 500, 118);
		quizButtonDown = new TextureRegion(quizTexture, 660, 178, 500,118);
		quizButtonUp.flip(false, true);
		quizButtonDown.flip(false, true);
		
				
		/* UI */
		logo = new TextureRegion(logoTexture, 0, 0, 512, 190);
		logo.flip(false, false);
		
		ntuLogo = new TextureRegion(logoTexture, 0, 0, 512, 190);
		ntuLogo.flip(false, true);
		
		ready = new TextureRegion(texture, 59, 83, 34, 7);
		ready.flip(false, true);

		retry = new TextureRegion(texture, 59, 110, 33, 7);
		retry.flip(false, true);
		
		gameOver = new TextureRegion(texture, 59, 92, 46, 7);
		gameOver.flip(false, true);

		scoreboard = new TextureRegion(texture, 111, 83, 97, 37);
		scoreboard.flip(false, true);

		star = new TextureRegion(texture, 152, 70, 10, 10);
		noStar = new TextureRegion(texture, 165, 70, 10, 10);

		star.flip(false, true);
		noStar.flip(false, true);

		highScore = new TextureRegion(texture, 59, 101, 48, 7);
		highScore.flip(false, true);

		ssLogo = new TextureRegion(ssLogoTexture, 0, 0, 1024, 414);
		ssLogo.flip(false, true);
		
		instruct = new TextureRegion(instructTexture, 0,0, 1024, 2048);
		instruct.flip(false, true);

		pauseMenu = new TextureRegion(pauseMenuTexture, 0, 0, 2895, 4096);
		pauseMenu.flip(false, true);
	
		
		/* Background & Floor */
		bg = new TextureRegion(bgTexture, 0, 0, 4087, 6672);
		bg.flip(false, true);

		grass = new TextureRegion(sbTexture, 0, 0, 1020, 32);
		grass.flip(false, true);
		
		question = new TextureRegion(quizTexture, 0, 0, 600, 900);
		question.flip(false, true);
 
		/* Danger */
		dd1 = new TextureRegion(DangerTexture, 0, 0, 133, 133);
		dd2 = new TextureRegion(DangerTexture, 133, 0, 133, 67);
		dd3 = new TextureRegion(DangerTexture, 266, 0, 67, 133);
		dd4 = new TextureRegion(DangerTexture, 333, 0, 67, 67);
		dd5 = new TextureRegion(DangerTexture, 400, 0, 40, 133);
		dd6 = new TextureRegion(DangerTexture, 0, 133, 267, 267);
		
		
		dd1.flip(false, true);
		dd2.flip(false, true);
		dd3.flip(false, true);
		dd4.flip(false, true);
		dd5.flip(false, true);
		dd6.flip(false, true);
		
		/* Power Ups */
		pp1 = new TextureRegion(PowerupTexture, 0, 0, 167, 167);
		pp2 = new TextureRegion(PowerupTexture, 167, 0, 250, 167);
		pp3 = new TextureRegion(PowerupTexture, 417, 0, 383, 167);
		pp4 = new TextureRegion(PowerupTexture, 0, 167, 354, 171);
		pp5 = new TextureRegion(PowerupTexture, 354, 167, 191, 182);
		
		pp1.flip(false, true);
		pp2.flip(true, true);
		pp3.flip(false, true);
		pp4.flip(false, true);
		pp5.flip(false, true);
		
		/* Main Character */
		birdDown = new TextureRegion(texture, 136, 0, 17, 12);
		birdDown.flip(false, true);

		bird = new TextureRegion(texture, 153, 0, 17, 12);
		bird.flip(false, true);

		birdUp = new TextureRegion(texture, 170, 0, 17, 12);
		birdUp.flip(false, true);
		
		shark = new TextureRegion(sharkTexture, 0, 0, 2049, 1024);
		shark.flip(false, true);

		/* Animation */
		TextureRegion[] birds = { birdDown, bird, birdUp };
		birdAnimation = new Animation(0.06f, birds);
		birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

		/* Audio */
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/bgm.mp3"));

		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

		/* Font */
		font = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		font.setScale(.25f, -.25f);

		whiteFont = new BitmapFont(Gdx.files.internal("data/whitetext.fnt"));
		whiteFont.setScale(.1f, -.1f);

		shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		shadow.setScale(.25f, -.25f);
		
		scoreFont = new BitmapFont(Gdx.files.internal("data/scoreText.fnt"));
		scoreFont.setScale(0.1f, -.1f);

		quizFont = new BitmapFont(Gdx.files.internal("data/quizFont.fnt"));
		quizFont.setScale(0.1f, -0.1f);
		
		quizFont1 = new BitmapFont(Gdx.files.internal("data/quizFont1.fnt"));
		quizFont1.setScale(.2f, -.2f);
		
		quizFont2 = new BitmapFont(Gdx.files.internal("data/quizFont2.fnt"));
		quizFont2.setScale(.2f, -.2f);
		
		credits = new BitmapFont(Gdx.files.internal("data/quizFont2.fnt"));
		credits.setScale(.1f, -.1f);
		
		// Create (or retrieve existing) preferences file
		prefs = Gdx.app.getPreferences("ZombieBird");

		if (!prefs.contains("highScore")) {
			prefs.putInteger("highScore", 0);
		}
	}

	public static void setHighScore(int val) {
		prefs.putInteger("highScore", val);
		prefs.flush();
	}

	public static int getHighScore() {
		return prefs.getInteger("highScore");
	}

	public static void dispose() {
		// We must dispose of the texture when we are finished.
		texture.dispose();
		logoTexture.dispose();
		DangerTexture.dispose();
		PowerupTexture.dispose(); 
		sharkTexture.dispose();
		bgTexture.dispose();
		sbTexture.dispose();
		pauseTexture.dispose();
		instructTexture.dispose();
		quizTexture.dispose();
		ssLogoTexture.dispose();
		buttonTexture.dispose();

		// Dispose sounds
		dead.dispose();
		flap.dispose();
		coin.dispose();
		
		bgm.dispose();

		font.dispose();
		whiteFont.dispose();
		quizFont.dispose();
		scoreFont.dispose();
		shadow.dispose();
		quizFont1.dispose();
		quizFont2.dispose();
		credits.dispose();
	}

}