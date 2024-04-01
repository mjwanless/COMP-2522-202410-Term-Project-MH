package ca.bcit.comp2522.termproject;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class IntroScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;

    public IntroScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        // Load an intro image or animation
        img = new Texture("badlogic.jpg"); // Replace with your intro background
    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro
    }

    @Override
    public void render(float delta) {
        game.clearScreen();
        batch.begin();
        // Render the intro image, text, or animations here
        batch.draw(img, 0, 0);
        batch.end();

        // Handle intro logic, such as timing and transitions
        // For example, after the intro is finished, switch to the game screen
        // game.setScreen(new GameScreen(game));
    }
    @Override
    public void resize(int width, int height) {
        // Resize the intro assets or elements
    }
    @Override
    public void pause() {
        // Pause any intro animations or sounds
    }
@Override
    public void resume() {
        // Resume any intro animations or sounds
    }
    @Override
    public void hide() {
        // Dispose of any intro assets or elements
    }
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

}
