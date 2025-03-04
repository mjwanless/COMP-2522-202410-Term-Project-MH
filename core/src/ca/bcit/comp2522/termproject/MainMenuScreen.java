package ca.bcit.comp2522.termproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Represents the main menu screen of the game.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class MainMenuScreen implements Screen {
    private final DiceGame game;
    private final SpriteBatch batch;
    private final Texture img;
    private final Stage stage;
    private final Skin skin;
    private final AssetManager assetManager;
    private Music mainMenuMusic;

    /**
     * Constructs a new MainMenuScreen object.
     * @param game The main game object.
     */
    public MainMenuScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/MainScreenBgTemp.jpg");

        // Initialize Stage and Skin. The stage manages UI elements (like buttons and labels).
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json")); // Ensure you have uiskin.json and related assets

        assetManager = new AssetManager();
        setupUI();
    }

    /**
     * Sets up the user interface components.
     */
    private void setupUI() {
        // Use a Table for layout
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create UI components
        Label titleLabel = new Label("Dice Adventure 2: Electric Boogaloo ", skin);
        TextButton newGameButton = new TextButton("New Game", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Add listeners to buttons
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(final com.badlogic.gdx.scenes.scene2d.InputEvent event, final float x, final float y) {
                // Change to the intro screen
                game.setScreen(new IntroScreen(game));
            }
        });

        // Add listener to the Exit button
        exitButton.addListener(new ClickListener() {
            @SuppressWarnings("checkstyle:FinalParameters")
            @Override
            public void clicked(final com.badlogic.gdx.scenes.scene2d.InputEvent event, final float x, final float y) {
                Gdx.app.exit(); // Exit the application
                System.exit(0); // Ensure that the application exits properly
            }
        });

        // Add components to the table
        table.add(titleLabel).padBottom(50);
        table.row(); // Start a new row
        table.add(newGameButton).padTop(10);
        // Add the Exit button to the table, below the New Game and Load Game buttons
        table.row(); // Start a new row
        table.add(exitButton).padTop(25);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage); // Important for capturing button clicks

        // Load and begin playing the music
        assetManager.load("Music/101 Presentiment.mp3", Music.class);
        assetManager.finishLoading();
        mainMenuMusic = assetManager.get("Music/101 Presentiment.mp3", Music.class);
        mainMenuMusic.setLooping(true);
        mainMenuMusic.play();
    }

    @Override
    public void render(final float delta) {
        game.clearScreen(); // A method to clear the screen
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        // No need to expand as this method is not used
    }

    @Override
    public void pause() {
        // No need to expand as this method is not used
    }

    @Override
    public void resume() {
        // No need to expand as this method is not used
    }

    @Override
    public void hide() {
        mainMenuMusic.stop();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        stage.dispose();
        skin.dispose();
        mainMenuMusic.dispose();
    }
}
