package ca.bcit.comp2522.termproject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

// This class is the main entry point of the program.
public class DiceGame extends Game {
	SpriteBatch batch;
	Texture img;
	public int combatCounter = 0;

//	The first thing that happens is this create method is called. It then creates a new SpriteBatch for optimized
//	rendering of images and sprites.
	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new MainMenuScreen(this)); // Set the main menu screen when the game starts
	}

	@Override
	public void render () {
		super.render(); // Important to call this to render the current screen

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	}

	// Utility method for clearing the screen, may be implemented in the future
	public void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
