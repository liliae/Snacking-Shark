package com.kilobolt.GameWorld;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.kilobolt.GameObjects.Background;
import com.kilobolt.GameObjects.Bird;
import com.kilobolt.GameObjects.Danger;
import com.kilobolt.GameObjects.Grass;
import com.kilobolt.GameObjects.Powerup;
import com.kilobolt.GameObjects.ScrollHandler;
import com.kilobolt.TweenAccessors.Value;
import com.kilobolt.TweenAccessors.ValueAccessor;
import com.kilobolt.ZBHelpers.AssetLoader;
import com.kilobolt.ZBHelpers.InputHandler;
import com.kilobolt.ui.SimpleButton;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

public class GameRenderer {

	private GameWorld myWorld;
	private Quiz quiz = new Quiz();
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;

	public SpriteBatch batcher;

	private int midPointX, midPointY;

	// Game Objects
	private Bird bird;
	private ScrollHandler scroller;
	private Grass frontGrass, backGrass;
	private Background frontBg, backBg;
	private Danger d1, d2, d3, d4, d5, d6;
	private Powerup p1, p2, p3, p4, p5;

	// Game Assets
	private TextureRegion bg, grass, shark, ready, ssLogo, ntuLogo, instruct, gameOver, highScore, scoreboard, star, noStar, pauseMenu,
			quizBg, retry, dd1, dd2, dd3, dd4, dd5, dd6, pp1, pp2, pp3, pp4, pp5;

	// Tween stuff
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons, pauseButtons, quizButtons, pauseUIButtons, settingsButtons, muteButtons,
			unmuteButtons, logInButtons, logOutButtons;
	private Color transitionColor;

	// Quiz statements
	public static int qns = 1;

	float WIDTH = Gdx.graphics.getWidth();
	float HEIGHT = Gdx.graphics.getHeight();
	float gameWidth = WIDTH / 2;
	float gameHeight = HEIGHT / (WIDTH / gameWidth);

	public GameRenderer(GameWorld world, int gameHeight, int midPointX, int midPointY) {
		myWorld = world;

		this.midPointX = midPointX;
		this.midPointY = midPointY;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMenuButtons();
		this.pauseButtons = ((InputHandler) Gdx.input.getInputProcessor()).getPauseButtons();
		this.quizButtons = ((InputHandler) Gdx.input.getInputProcessor()).getQuizButtons();
		this.pauseUIButtons = ((InputHandler) Gdx.input.getInputProcessor()).getPauseUIButtons();
		this.settingsButtons = ((InputHandler) Gdx.input.getInputProcessor()).getSettingsButtons();
		this.muteButtons = ((InputHandler) Gdx.input.getInputProcessor()).getMuteButtons();
		this.unmuteButtons = ((InputHandler) Gdx.input.getInputProcessor()).getUnMuteButtons();
		this.logInButtons = ((InputHandler) Gdx.input.getInputProcessor()).getLogInButtons();
		this.logOutButtons = ((InputHandler) Gdx.input.getInputProcessor()).getLogOutButtons();

		cam = new OrthographicCamera();
		cam.setToOrtho(true, gameWidth, gameHeight);

		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);

		// Actual Rendering
		initGameObjects();
		initAssets();

		transitionColor = new Color();
		prepareTransition(255, 255, 255, .5f);

	}

	private void initGameObjects() {
		bird = myWorld.getBird();
		scroller = myWorld.getScroller();
		frontGrass = scroller.getFrontGrass();
		backGrass = scroller.getBackGrass();
		frontBg = scroller.getFrontBG();
		backBg = scroller.getBackBG();

		d1 = scroller.getDanger1();
		d2 = scroller.getDanger2();
		d3 = scroller.getDanger3();
		d4 = scroller.getDanger4();
		d5 = scroller.getDanger5();
		d6 = scroller.getDanger6();

		p1 = scroller.getPowerup1();
		p2 = scroller.getPowerup2();
		p3 = scroller.getPowerup3();
		p4 = scroller.getPowerup4();
		p5 = scroller.getPowerup5();
	}

	private void initAssets() { // load from assetloader
		bg = AssetLoader.bg;
		grass = AssetLoader.grass;
		shark = AssetLoader.shark;

		dd1 = AssetLoader.dd1;
		dd2 = AssetLoader.dd2;
		dd3 = AssetLoader.dd3;
		dd4 = AssetLoader.dd4;
		dd5 = AssetLoader.dd5;
		dd6 = AssetLoader.dd6;

		pp1 = AssetLoader.pp1;
		pp2 = AssetLoader.pp2;
		pp3 = AssetLoader.pp3;
		pp4 = AssetLoader.pp4;
		pp5 = AssetLoader.pp5;

		ready = AssetLoader.ready;
		ssLogo = AssetLoader.ssLogo;
		ntuLogo = AssetLoader.ntuLogo;
		gameOver = AssetLoader.gameOver;
		highScore = AssetLoader.highScore;
		scoreboard = AssetLoader.scoreboard;
		retry = AssetLoader.retry;
		star = AssetLoader.star;
		noStar = AssetLoader.noStar;
		pauseMenu = AssetLoader.pauseMenu;

		instruct = AssetLoader.instruct;
		quizBg = AssetLoader.question;
	}

	private void drawGrass() {
		// Draw the grass
		batcher.draw(grass, frontGrass.getX(), frontGrass.getY(), frontGrass.getWidth(), 20);
		batcher.draw(grass, backGrass.getX(), backGrass.getY(), backGrass.getWidth(), 20);
	}

	private void drawBg() {
		// Draw the grass
		batcher.draw(bg, frontBg.getX(), 0, frontBg.getWidth(), gameHeight);
		batcher.draw(bg, backBg.getX(), 0, backBg.getWidth(), gameHeight);
	}

	private void drawDanger() {

		batcher.draw(dd1, d1.getX(), d1.getY(), 100, 100); // Coral 1
		batcher.draw(dd2, d2.getX(), d2.getY(), 100, 50); // Coral 2
		batcher.draw(dd3, d3.getX(), d3.getY(), 50, 100); // Coral 3
		batcher.draw(dd4, d4.getX(), d4.getY(), 50, 50); // Plastic Bag
		batcher.draw(dd5, d5.getX(), d5.getY(), 15, 50); // Bottle
		batcher.draw(dd6, d6.getX() - 43, d6.getY() - 50, 150, 150); // Trawler
	}

	private void drawPowerup() {

		batcher.draw(pp1, p1.getX(), p1.getY(), 50, 50);
		batcher.draw(pp2, p2.getX(), p2.getY(), 75, 50);
		batcher.draw(pp3, p3.getX(), p3.getY(), 114, 50);
		batcher.draw(pp4, p4.getX(), p4.getY(), 106, 51);
		batcher.draw(pp5, p5.getX(), p5.getY(), 57, 54);
	}

	private void drawBirdCentered(float runTime) {
		batcher.draw(shark, midPointX, bird.getY() - 15, bird.getWidth() / 2.0f, bird.getHeight() / 2.0f,
				bird.getWidth(), bird.getHeight(), 5, 5, bird.getRotation());
	}

	private void drawBird(float runTime) {

		batcher.draw(shark, bird.getX(), bird.getY(), bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(),
				bird.getHeight(), 5, 5, bird.getRotation());

	}

	private void drawMenuUI() {
		batcher.draw(ssLogo, midPointX - (150 / 2), bird.getY() - midPointY / 2, 150, 60.6f);

		for (SimpleButton button : menuButtons) {
			button.draw(batcher);
		}

	}

	private void drawPauseUI() {
		Gdx.gl.glClearColor(1, 1, 1, 0.8f);
		batcher.draw(pauseMenu, 10, 10, gameWidth-20, gameHeight-20);

		for (SimpleButton button : pauseUIButtons) {
			button.draw(batcher);
		}

	}

	private void drawSettingsUI() {

		String back = "Press 'B' to return to previous screen";
		AssetLoader.credits.drawWrapped(batcher, back, 50 , 10, 200, HAlignment.CENTER);

		if (!myWorld.mute) {
			for (SimpleButton button : muteButtons) {
				button.draw(batcher);
			}
		} else if (myWorld.mute) {
			for (SimpleButton button : unmuteButtons) {
				button.draw(batcher);
			}
		}

		if (!myWorld.loggedIn) {
			for (SimpleButton button : logInButtons) {
				button.draw(batcher);
			}
		} else if (myWorld.loggedIn) {
			for (SimpleButton button : logOutButtons) {
				button.draw(batcher);
			}
		}
		for (SimpleButton button : settingsButtons) {
			button.draw(batcher);
		}

	}

	private void drawPauseB() {
		for (SimpleButton button : pauseButtons) {
			button.draw(batcher);
		}

	}

	private void drawInstruct() {
		Gdx.gl.glClearColor(1 / 255f, 1 / 255f, 1 / 255f, 0.2f);
		batcher.draw(instruct, 0, 0, gameWidth, gameHeight - 10);
	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, midPointX - (scoreboard.getRegionWidth() / 2.0f), midPointY - 30, 97, 37);

		batcher.draw(noStar, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 3, midPointY - 15, 10, 10);
		batcher.draw(noStar, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 15, midPointY - 15, 10, 10);
		batcher.draw(noStar, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 27, midPointY - 15, 10, 10);
		batcher.draw(noStar, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 39, midPointY - 15, 10, 10);
		batcher.draw(noStar, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 51, midPointY - 15, 10, 10);

		if (myWorld.getScore() > 2) {
			batcher.draw(star, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 51, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 17) {
			batcher.draw(star, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 39, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 50) {
			batcher.draw(star, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 27, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 80) {
			batcher.draw(star, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 15, midPointY - 15, 10, 10);
		}

		if (myWorld.getScore() > 120) {
			batcher.draw(star, midPointX - (scoreboard.getRegionWidth() / 2.0f) + 3, midPointY - 15, 10, 10);
		}

		AssetLoader.whiteFont.draw(batcher, "" + myWorld.getScore(), midPointX + (scoreboard.getRegionWidth() / 4.0f),
				midPointY - 20);

		AssetLoader.whiteFont.draw(batcher, "" + AssetLoader.getHighScore(),
				midPointX + (scoreboard.getRegionWidth() / 4.0f), midPointY - 3);

	}

	private void drawRetry() {
		batcher.draw(retry, midPointX - (retry.getRegionWidth()), midPointY + 10, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, midPointX - (ready.getRegionWidth()), midPointY - 50, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, midPointX - (gameOver.getRegionWidth()), midPointY - 50, 92, 14);
	}

	private void drawScore() {
		AssetLoader.scoreFont.draw(batcher, "SCORE: " + "" + myWorld.getScore(), 0, midPointY / 2);

		AssetLoader.scoreFont.draw(batcher, "HIGHSCORE: " + "" + AssetLoader.getHighScore(), 0, midPointY / 2 - 10);
	}

	public void drawQns(int qns) {

		batcher.draw(quizBg, midPointX - (quizBg.getRegionWidth() / 4.0f) + 10, midPointY / 2 - 50, gameWidth - 20,
				gameHeight - 20);

		AssetLoader.quizFont2.drawWrapped(batcher, quiz.getQuestion(qns),
				midPointX - (quizBg.getRegionWidth() / 4.0F) + 20, midPointY / 2 - 30, gameWidth - 40,
				HAlignment.CENTER);

	}

	public void drawChoices(int qns) {

		for (SimpleButton button : quizButtons) {
			button.draw(batcher);
		}

		AssetLoader.quizFont1.drawWrapped(batcher, quiz.getChoice1(qns),
				midPointX - (AssetLoader.quizButtonUp.getRegionWidth() / 4), midPointY + 15, gameWidth - 60,
				HAlignment.CENTER);
		AssetLoader.quizFont1.drawWrapped(batcher, quiz.getChoice2(qns),
				midPointX - (AssetLoader.quizButtonUp.getRegionWidth() / 4), (midPointY * 6 / 5) + 25, gameWidth - 60,
				HAlignment.CENTER);
		AssetLoader.quizFont1.drawWrapped(batcher, quiz.getChoice3(qns),
				midPointX - (AssetLoader.quizButtonUp.getRegionWidth() / 4), (midPointY * 7 / 5) + 35, gameWidth - 60,
				HAlignment.CENTER);

	}

	private void drawHighScore() {
		batcher.draw(highScore, midPointX, midPointY - highScore.getRegionWidth(), 96, 14);
	}

	private void drawCredits(){
		String Credits1 ="EE3080: Design and Innovation Project" + "\n" +"Fun Learning on The Move via Online Social Media" + "\n"+
						"Nanyang Technological University" + "\n\n" + "Alvin Lai Jun Hui" + "\n" +"Cherie Chan Ling" + "\n" + "Choong Li Qing"
				+ "\n" + "Elvira Febiani" + "\n" + "Kanchan Pandey" + "\n" + "Luo Mengwei" + "\n" + "Madasamy Ravi Nadar Mathana" +
				"\n" + "Nageshwari" + "\n" + "Wang Luyu" + "\n" + "Wang Rui";
		
		String Credits2 = "Special Thanks to Kilobolt LC for their codes and tutorials in making this possible and libGDX for the libraries used in this game.";
		
		String version = "Beta Testing Version for Desktop";
		
		batcher.draw(ntuLogo, midPointX - 100, 50, 200, 74);
		AssetLoader.credits.drawMultiLine(batcher, Credits1, 50, 135, 200, HAlignment.CENTER);
		AssetLoader.credits.drawWrapped(batcher, Credits2, 50, midPointY + 100, 200, HAlignment.CENTER);
		AssetLoader.credits.drawWrapped(batcher, version, 50, gameHeight - 50, 200, HAlignment.CENTER);

		String back = "Press 'B' to return to previous screen";
		AssetLoader.credits.drawWrapped(batcher, back, 50 , 10, 200, HAlignment.CENTER);
	}

	
	public void render(float delta, float runTime) {

		Gdx.gl.glClearColor(23 / 255f, 99 / 255f, 173 / 255f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		batcher.disableBlending();

		drawBg();
		batcher.enableBlending();

		drawDanger();
		drawPowerup();

		if (myWorld.isRunning()) {
			drawBird(runTime);
			drawScore();
			drawPauseB();
		} else if (myWorld.isReady()) {
			drawBird(runTime);
			drawReady();
		} else if (myWorld.isMenu()) {
			drawBirdCentered(runTime);
			drawMenuUI();
		} else if (myWorld.isPaused()) {
			drawBird(0);
			drawPauseUI();
		} else if (myWorld.isInstructions()) {
			drawInstruct();
		} else if (myWorld.isSettings()) {
			drawBg();
			drawSettingsUI();
		} else if (myWorld.isGameOver()) {
			drawBird(runTime);
			drawGameOver();
			drawScoreboard();
			drawRetry();
		} else if (myWorld.isHighScore()) {
			drawBird(runTime);
			drawScoreboard();
			drawHighScore();
			drawRetry();
		} else if (myWorld.isQuiz()) {
			drawBg();
			drawQns(myWorld.qns);
			drawChoices(myWorld.qns);
		} else if (myWorld.isCredits()) {
			drawCredits();
		}

		drawGrass();
		batcher.end();

		shapeRenderer.begin(ShapeType.Filled);

		// Draw Sandbed base
		shapeRenderer.setColor(231 / 255f, 205 / 255f, 158 / 255f, 1 / 255f);
		shapeRenderer.rect(0, 2 * midPointY - 10, WIDTH, 15);

		/*
		Gdx.gl.glEnable(GL10.GL_BLEND);

		shapeRenderer.setColor(0 / 255f, 59 / 255f, 255 / 255f, 0.32f);
		shapeRenderer.rect(myWorld.sky.getX(), myWorld.sky.getY(), myWorld.sky.getWidth(), myWorld.sky.getHeight());

		shapeRenderer.setColor(255 / 255f, 0 / 255f, 216 / 255f, 0.32f);
		shapeRenderer.rect(d1.getD1().x, d1.getD1().y, d1.getD1().width, d1.getD1().height);
		shapeRenderer.rect(d2.getD2().x, d2.getD2().y, d2.getD2().width, d2.getD2().height);
		shapeRenderer.rect(d3.getD3().x, d3.getD3().y, d3.getD3().width, d3.getD3().height);
		shapeRenderer.rect(d4.getD4().getX(), d4.getD4().getY(), d4.getD4().getWidth(), d4.getD4().getHeight());
		shapeRenderer.rect(d5.getD5().getX(), d5.getD5().getY(), d5.getD5().getWidth(), d5.getD5().getHeight());
		shapeRenderer.rect(d6.getD6().getX(), d6.getD6().getY(), d6.getD6().getWidth(), d6.getD6().getHeight());

		shapeRenderer.setColor(8 / 255f, 0 / 255f, 122 / 255f, 0.32f);
		shapeRenderer.circle(p1.getP1().x, p1.getP1().y, p1.getP1().radius);
		shapeRenderer.rect(p2.getP2().x, p2.getP2().y, p2.getP2().width, p2.getP2().height);
		shapeRenderer.rect(p3.getP3().x, p3.getP3().y, p3.getP3().width, p3.getP3().height);
		shapeRenderer.rect(p4.getP4().x, p4.getP4().y, p4.getP4().width, p4.getP4().height);
		shapeRenderer.circle(p5.getP5().x, p5.getP5().y, p5.getP5().radius);

		shapeRenderer.setColor(Color.RED);
		shapeRenderer.circle(bird.getBoundingCircle().x, bird.getBoundingCircle().y, bird.getBoundingCircle().radius);

		shapeRenderer.rect(midPointX, 0, 1, gameHeight);
		shapeRenderer.rect(0, midPointY, gameWidth, 1);
		 */
		
		shapeRenderer.end();

		drawTransition(delta);

	}

	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0).ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL10.GL_BLEND);
			Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g, transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, WIDTH, HEIGHT);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL10.GL_BLEND);

		}
	}

}
