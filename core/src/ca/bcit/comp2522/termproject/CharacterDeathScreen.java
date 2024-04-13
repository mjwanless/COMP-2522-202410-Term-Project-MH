package ca.bcit.comp2522.termproject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
/**
 * Screen displayed when the character dies.
 * This screen allows the player to start a new game or exit the application.
 * The screen displays a background image and two buttons.
 * The "New Game" button will take the player to the character selection screen.
 * The "Exit" button will close the application.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class CharacterDeathScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private final Texture img;
    private ShapeRenderer shapeRenderer;
    private final AssetManager assetManager;
    private Music characterDeathMusic;
    private final Stage stage;
    private final Skin skin;

    /**
     * Constructs a CharacterDeathScreen object.
     *
     * @param game The game instance.
     */
    public CharacterDeathScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/defeat.jpg");

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        assetManager = new AssetManager();
        setupUI();
    }


    private void setupUI() {
        // Use a Table for layout
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Create UI components
        Label defeatLabel = new Label("Face The Sorrow of DEFEAT", skin, "default");
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
            @Override
            public void clicked(final com.badlogic.gdx.scenes.scene2d.InputEvent event, final float x, final float y) {
                Gdx.app.exit(); // Exit the application
                System.exit(0); // Ensure that the application exits properly
            }
        });

        final int padding = 10;

        // Add the defeat label to the table
        table.add(defeatLabel).padTop(padding);


        table.row(); // Start a new row
        table.add(newGameButton).padTop(padding);
        table.row(); // Start another new row

        // Add the Exit button to the table, below the New Game and Load Game buttons
        table.row(); // Start a new row
        table.add(exitButton).padTop(padding);
    }
    /**
     * Prepares assets and variables for the character death screen, including initializing
     * the sprite batch, shape renderer, and input processor.
     * Loads and plays the character death music.
     */
    @Override
    public void show() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);

        // Load and play character death music
        assetManager.load("Music/111 Secret of the Forest.mp3", Music.class);
        assetManager.finishLoading();
        characterDeathMusic = assetManager.get("Music/111 Secret of the Forest.mp3", Music.class);
        characterDeathMusic.setLooping(true);
        characterDeathMusic.play();
    }

    /**
     * Renders the character death screen, including background and stage elements.
     *
     * @param delta the time in seconds since the last render
     */
    @Override
    public void render(final float delta) {
        // Clear screen
        game.clearScreen();

        // Draw background
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Update and draw stage
        final float actFl = 30f; // Controls the maximum act frequency
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / actFl));
        stage.draw();
    }

    /**
     * Handles screen resizing.
     *
     * @param width  the new width of the screen
     * @param height the new height of the screen
     */
    @Override
    public void resize(final int width, final int height) {
        // If you need to handle screen resizing, do it here
    }

    /**
     * Called when the application is paused.
     * Implement if there is a need to pause your screen.
     */
    @Override
    public void pause() {
        // Implement if there is a need to pause your screen
    }

    /**
     * Called when the application is resumed from a paused state.
     * Implement if there is a need to resume your screen.
     */
    @Override
    public void resume() {
        // Implement if there is a need to resume your screen
    }

    /**
     * Called when this screen is no longer the current screen for a {@link Game}.
     * Stops the character death music and disposes of any resources used by the screen.
     */
    @Override
    public void hide() {
        characterDeathMusic.stop();
        // Implement if there is anything you need to hide
    }

    /**
     * Releases all resources of the screen.
     * Disposes of the sprite batch, shape renderer, background image texture,
     * character death music, and stage.
     */
    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
        if (img != null) {
            img.dispose();
        }
        characterDeathMusic.dispose();
        stage.dispose();
    }
}
