package ca.bcit.comp2522.termproject;

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

public class CharacterDeathScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;
    private Music characterDeathMusic;
    private Stage stage;
    private Skin skin;


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
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Change to the intro screen
                game.setScreen(new IntroScreen(game));
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

        // Add the defeat label to the table
        table.add(defeatLabel).padTop(10);


        table.row(); // Start a new row
        table.add(newGameButton).padTop(10);
        table.row(); // Start another new row

        // Add the Exit button to the table, below the New Game and Load Game buttons
        table.row(); // Start a new row
        table.add(exitButton).padTop(10);
    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);

        // Load and play music
        assetManager.load("Music/111 Secret of the Forest.mp3", Music.class);
        assetManager.finishLoading();
        characterDeathMusic = assetManager.get("Music/111 Secret of the Forest.mp3", Music.class);
        characterDeathMusic.setLooping(true);
        characterDeathMusic.play();
    }

    @Override
    public void render(float delta) {
        game.clearScreen();

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Handle additional rendering or logic here

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }
    @Override
    public void resize(int width, int height) {
        // If you need to handle screen resizing, do it here
    }

    @Override
    public void pause() {
        // Implement if there is a need to pause your screen
    }

    @Override
    public void resume() {
        // Implement if there is a need to resume your screen
    }

    @Override
    public void hide() {
        characterDeathMusic.stop();
        // Implement if there is anything you need to hide
    }

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
