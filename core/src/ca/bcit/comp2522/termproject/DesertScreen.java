package ca.bcit.comp2522.termproject;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DesertScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private AssetManager assetManager;
    private Music desertScreenMusic;

    public DesertScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        // Load an intro image or animation
        img = new Texture("backgrounds/desert_background.jpeg");
        assetManager = new AssetManager();

    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro

        assetManager.load("Music/201 Ruined World.mp3", Music.class);
        assetManager.finishLoading();
        desertScreenMusic = assetManager.get("Music/201 Ruined World.mp3", Music.class);
        desertScreenMusic.setLooping(true);
        desertScreenMusic.play();
    }

    @Override
    public void render(float delta) {
        game.clearScreen();
        batch.begin();
        // Render the intro image, text, or animations here
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        desertScreenMusic.stop();
    }
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        desertScreenMusic.dispose();
    }

}
