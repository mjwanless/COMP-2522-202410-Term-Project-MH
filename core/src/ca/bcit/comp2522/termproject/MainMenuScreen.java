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

public class MainMenuScreen implements Screen {
    private final DiceGame game;
    private final SpriteBatch batch;
    private final Texture img;
    private Stage stage;
    private Skin skin;
    private AssetManager assetManager;
    private Music mainMenuMusic;

    public MainMenuScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        img = new Texture("MainScreenBgTemp.jpg");

        // Initialize Stage and Skin. The stage manages UI elements (like buttons and labels).
        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json")); // Ensure you have uiskin.json and related assets

        assetManager = new AssetManager();
        setupUI();
    }

    private void setupUI() {
        // Use a Table for layout
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        // Optional: Enable debug lines
        // table.setDebug(true);

        // Create UI components
        Label titleLabel = new Label("Dice Adventure 2: Electric Boogaloo ", skin);
        TextButton newGameButton = new TextButton("New Game", skin);
        TextButton loadGameButton = new TextButton("Load Game", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        // Add listeners to buttons
        newGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Change to the intro screen
                game.setScreen(new IntroScreen(game));
            }

        });

        loadGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Handle load game action
            }
        });

        // Add listener to the Exit button
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                Gdx.app.exit(); // Exit the application
                System.exit(0); // Ensure that the application exits properly
            }
        });

        // Add components to the table
        table.add(titleLabel).padBottom(20);
        table.row(); // Start a new row
        table.add(newGameButton).padTop(10);
        table.row(); // Start another new row
        table.add(loadGameButton).padTop(10);

        // Add the Exit button to the table, below the New Game and Load Game buttons
        table.row(); // Start a new row
        table.add(exitButton).padTop(10);
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
    public void render(float delta) {
        game.clearScreen(); // A method to clear the screen, you might need to implement this in DiceGame
        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(final int width, final int height) {
        // no-op
    }

    @Override
    public void pause() {
        // no-op
    }

    @Override
    public void resume() {
        // no-op
    }

    @Override
    public void hide() {
        mainMenuMusic.stop();
        // no-op
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        stage.dispose(); // Dispose of the stage to clean up resources
        skin.dispose();
        mainMenuMusic.dispose();
    }
}
