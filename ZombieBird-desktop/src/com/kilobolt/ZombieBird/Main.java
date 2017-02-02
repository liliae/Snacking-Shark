package com.kilobolt.ZombieBird;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Snackin' Shark";
		cfg.useGL20 = false;
		cfg.width = 600 ;
		cfg.height = 900;
		
		new LwjglApplication(new ZBGame(), cfg);
	}
}
