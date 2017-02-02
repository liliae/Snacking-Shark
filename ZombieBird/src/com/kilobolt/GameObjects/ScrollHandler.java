package com.kilobolt.GameObjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.kilobolt.GameWorld.GameWorld;
import com.kilobolt.ZBHelpers.AssetLoader;

public class ScrollHandler {

	private static Random r = new Random();

	private Grass frontGrass, backGrass;
	private Background frontBG, backBG;
	private Danger d1, d2, d3, d4, d5, d6;
	private Powerup p1, p2, p3, p4, p5;
	public static Powerup powerup;

	public static final int SCROLL_SPEED = -59;
	public static int PIPE_GAP1, PIPE_GAP2, Y1, Y2;

	private static GameWorld gameWorld;

	public static Bird bird;

	int screenWidth = Gdx.graphics.getWidth();
	int screenHeight = Gdx.graphics.getHeight();
	int gameWidth = screenWidth/2;
	int gameHeight = (int) (screenHeight / (screenWidth / gameWidth));
	
	
	public ScrollHandler(GameWorld gameWorld, float yPos) {
		this.gameWorld = gameWorld;
		frontGrass = new Grass(0, yPos, gameWidth, 30, SCROLL_SPEED);
		backGrass = new Grass(frontGrass.getTailX()-2, yPos, gameWidth, 30, SCROLL_SPEED);
		
		frontBG = new Background(0, 0, gameWidth, gameHeight, SCROLL_SPEED);
		backBG = new Background(frontBG.getTailX()-3, 0, gameWidth, gameHeight, SCROLL_SPEED);

		Y1 = r.nextInt(gameHeight-100);
		Y2 = r.nextInt(gameHeight);

		PIPE_GAP1 = r.nextInt(gameWidth/2); // Danger Gaps
		PIPE_GAP2 = r.nextInt(gameWidth/2); // Powerup Gaps

		/* p1 - crab
		 * p2 - seal
		 * p3 - stingray
		 * p4 - turtle
		 * p5 - starfish
		 */
		
		p1 = new Powerup(gameWidth + 100, gameHeight - 100, 50, 50, SCROLL_SPEED/2);
		p2 = new Powerup(p1.getTailX() + PIPE_GAP2, Y1, 75, 50, SCROLL_SPEED);
		p3 = new Powerup(p2.getTailX() + PIPE_GAP2, Y1, 114, 50, 2*SCROLL_SPEED);
		p4 = new Powerup(p3.getTailX() + PIPE_GAP2, Y1, 106, 51, SCROLL_SPEED/2);
		p5 = new Powerup(p4.getTailX() + PIPE_GAP2, gameHeight - 100, 57, 54, SCROLL_SPEED/4);
		
		/* d1 - coral 1
		 * d2 - coral 2
		 * d3 - coral 3
		 * d4 - plastic bag
		 * d5 - bottle
		 * d6 - trawler */
		
		d1 = new Danger(gameWidth + 50, gameHeight - 100, 100, 100, SCROLL_SPEED);
		d2 = new Danger(d1.getTailX() + PIPE_GAP1, gameHeight - 70, 100, 50, SCROLL_SPEED);
		d3 = new Danger(d2.getTailX() + PIPE_GAP1, gameHeight - 70, 50, 100, SCROLL_SPEED);
		d4 = new Danger(d3.getTailX() + PIPE_GAP1, Y2, 50, 50, SCROLL_SPEED);
		d5 = new Danger(d4.getTailX() + PIPE_GAP1, Y2, 15, 50, SCROLL_SPEED);
		d6 = new Danger(d5.getTailX() + PIPE_GAP1, 0, 50, 100, 2.5f*SCROLL_SPEED);

	}

	public void updateReady(float delta) {

		frontGrass.update(delta);
		backGrass.update(delta);
		frontBG.update(delta);
		backBG.update(delta);

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX()-2);

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX()-2);

		}

		if (frontBG.isScrolledLeft()) {
			frontBG.reset(backBG.getTailX()-3);

		} else if (backBG.isScrolledLeft()) {
			backBG.reset(frontBG.getTailX()-3);

		}

	}

	public void update(float delta) {
		// Update our objects
		frontGrass.update(delta);
		backGrass.update(delta);
		frontBG.update(delta);
		backBG.update(delta);

		d1.update(delta);
		d2.update(delta);
		d3.update(delta);
		d4.update(delta);
		d5.update(delta);
		d6.update(delta);

		p1.update(delta);
		p2.update(delta);
		p3.update(delta);
		p4.update(delta);
		p5.update(delta);

		// Check if any of the dangers are scrolled left,
		// and reset accordingly
		// reset(float newX)
		if (d1.isScrolledLeft()) {
			d1.reset(d3.getTailX() + PIPE_GAP1 + gameWidth);
		} else if (d2.isScrolledLeft()) {
			d2.reset(d1.getTailX() + PIPE_GAP1+ gameWidth);
		} else if (d3.isScrolledLeft()) {
			d3.reset(d1.getTailX() + PIPE_GAP1 + gameWidth);
		} else if (d4.isScrolledLeft()) {
			d4.reset(d5.getTailX() + PIPE_GAP1 + gameWidth);
		} else if (d5.isScrolledLeft()) {
			d5.reset(d6.getTailX() + PIPE_GAP1 + gameWidth);
		} else if (d6.isScrolledLeft()) {
			d6.reset(d4.getTailX() + PIPE_GAP1 + gameWidth);
		}

		// Check if powerups are scrolled left

		if (p1.isScrolledLeft()) {
			p1.reset(p5.getTailX() + PIPE_GAP2 + gameWidth);
		} else if (p2.isScrolledLeft()) {
			p2.reset(p1.getTailX() + PIPE_GAP2+ gameWidth);
		} else if (p3.isScrolledLeft()) {
			p3.reset(p2.getTailX() + PIPE_GAP2+ gameWidth);
		} else if (p4.isScrolledLeft()) {
			p4.reset(p3.getTailX() + PIPE_GAP2+ gameWidth);
		} else if (p5.isScrolledLeft()) {
			p5.reset(p4.getTailX() + PIPE_GAP2+ gameWidth);
		}

		// Same with grass
		if (frontGrass.isScrolledLeft()) {
			frontGrass.reset(backGrass.getTailX()-2);

		} else if (backGrass.isScrolledLeft()) {
			backGrass.reset(frontGrass.getTailX()-2);

		}

		// Same with Bg
		if (frontBG.isScrolledLeft()) {
			frontBG.reset(backBG.getTailX()-3);

		} else if (backBG.isScrolledLeft()) {
			backBG.reset(frontBG.getTailX()-3);

		}
	}

	public void stop() {
		frontGrass.stop();
		backGrass.stop();

		frontBG.stop();
		backBG.stop();

		d1.stop();
		d2.stop();
		d3.stop();
		d4.stop();
		d5.stop();
		d6.stop();

		p1.stop();
		p2.stop();
		p3.stop();
		p4.stop();
		p5.stop();

		bird.die();
	}

	public boolean collides(Bird bird) { // Return true if any hits bird

		ScrollHandler.bird = bird;

		if (Intersector.overlaps(bird.boundingCircle, p1.getP1())
				|| Intersector.overlaps(bird.boundingCircle, p2.getP2())
				|| Intersector.overlaps(bird.boundingCircle, p3.getP3())
				|| Intersector.overlaps(bird.boundingCircle, p4.getP4())
				|| Intersector.overlaps(bird.boundingCircle, p5.getP5())) {

			if (!p1.isScored() && Intersector.overlaps(bird.boundingCircle, p1.getP1())) {
				p1.setScored(true); // true becos add 1
				p1.onRestart(p5.getTailX() + PIPE_GAP2, SCROLL_SPEED/2);
				p1.setReset(true);
			}

			else if (!p2.isScored() && Intersector.overlaps(bird.boundingCircle, p2.getP2())) {
				p2.setScored(true);

				if (p1.hasRestarted()) {
					p2.onRestart(p1.getTailX() + PIPE_GAP2, SCROLL_SPEED);
				} else
					p2.onRestart(p5.getTailX() + PIPE_GAP2, SCROLL_SPEED);
				p2.setReset(true);
			}

			else if (!p3.isScored() && Intersector.overlaps(bird.boundingCircle, p3.getP3())) {
				p3.setScored(true);

				if (p2.hasRestarted())
					p3.onRestart(p2.getTailX() + PIPE_GAP2, 2*SCROLL_SPEED);
				else if (p1.hasRestarted())
					p3.onRestart(p1.getTailX() + PIPE_GAP2, 2*SCROLL_SPEED);
				else
					p3.onRestart(p5.getTailX() + PIPE_GAP2, 2*SCROLL_SPEED);
				p3.setReset(true);
			}

			else if (!p4.isScored() && Intersector.overlaps(bird.boundingCircle, p4.getP4())) {
				p4.setScored(true);

				if (p3.hasRestarted())
					p4.onRestart(p3.getTailX() + PIPE_GAP2, SCROLL_SPEED/2);
				else if (p2.hasRestarted())
					p4.onRestart(p2.getTailX() + PIPE_GAP2, SCROLL_SPEED/2);
				else if (p1.hasRestarted())
					p4.onRestart(p1.getTailX() + PIPE_GAP2, SCROLL_SPEED/2);
				else
					p4.onRestart(p5.getTailX() + PIPE_GAP2, SCROLL_SPEED/2);
				p4.setReset(true);
			}

			else if (!p5.isScored() && Intersector.overlaps(bird.boundingCircle, p5.getP5())) {
				p5.setScored(true);

				if (p4.hasRestarted())
					p5.onRestart(p4.getTailX() + PIPE_GAP2, SCROLL_SPEED/4);
				else if (p3.hasRestarted())
					p5.onRestart(p3.getTailX() + PIPE_GAP2, SCROLL_SPEED/4);
				else if (p2.hasRestarted())
					p5.onRestart(p2.getTailX() + PIPE_GAP2, SCROLL_SPEED/4);
				else
					p5.onRestart(p1.getTailX() + PIPE_GAP2, SCROLL_SPEED/4);
				p5.setReset(true);
			}

			
			gameWorld.quiz();
			
			if(!gameWorld.mute){
			AssetLoader.coin.play();}

			return false;
		}

		else if (d1.collides(bird) || d2.collides(bird) || d3.collides(bird) || d4.collides(bird) || d5.collides(bird)
				|| d6.collides(bird))
			return true; // danger needs to die

		else
			return false;
	}

	public static void addScore(int increment) {
		gameWorld.addScore(increment);
	}

	public Background getFrontBG() {
		return frontBG;
	}

	public Background getBackBG() {
		return backBG;
	}

	public Grass getFrontGrass() {
		return frontGrass;
	}

	public Grass getBackGrass() {
		return backGrass;
	}

	public Danger getDanger1() {
		return d1;
	}

	public Danger getDanger2() {
		return d2;
	}

	public Danger getDanger3() {
		return d3;
	}

	public Danger getDanger4() {
		return d4;
	}

	public Danger getDanger5() {
		return d5;
	}

	public Danger getDanger6() {
		return d6;
	}

	public void onRestart() {
		frontGrass.onRestart(0, SCROLL_SPEED);
		backGrass.onRestart(frontGrass.getTailX(), SCROLL_SPEED);
		frontBG.onRestart(0, SCROLL_SPEED);
		backBG.onRestart(frontBG.getTailX(), SCROLL_SPEED);

		d1.onRestart(gameWidth + 50, SCROLL_SPEED);
		d2.onRestart(d1.getTailX() + PIPE_GAP1, SCROLL_SPEED);
		d3.onRestart(d2.getTailX() + PIPE_GAP1, SCROLL_SPEED);
		d4.onRestart(d3.getTailX() + PIPE_GAP1, SCROLL_SPEED);
		d5.onRestart(d4.getTailX() + PIPE_GAP1, SCROLL_SPEED);
		d6.onRestart(d5.getTailX() + PIPE_GAP1, 2*SCROLL_SPEED);

		p1.onRestart(gameWidth + 100, SCROLL_SPEED/2);
		p2.onRestart(p1.getTailX() + PIPE_GAP2, SCROLL_SPEED);
		p3.onRestart(p2.getTailX() + PIPE_GAP2, 2*SCROLL_SPEED);
		p4.onRestart(p3.getTailX() + PIPE_GAP2, SCROLL_SPEED/2);
		p5.onRestart(p4.getTailX() + PIPE_GAP2, SCROLL_SPEED/4);
	}

	public Powerup getPowerup1() {
		return p1;
	}

	public Powerup getPowerup2() {
		return p2;
	}

	public Powerup getPowerup3() {
		return p3;
	}

	public Powerup getPowerup4() {
		return p4;
	}

	public Powerup getPowerup5() {
		return p5;
	}

}
