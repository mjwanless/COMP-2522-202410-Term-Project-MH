package ca.bcit.comp2522.termproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class IntroScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private AssetManager assetManager;
    private Music introScreenMusic;
    private Stage stage;
    private Skin skin;

    public IntroScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        // Load an intro image or animation
        img = new Texture("backgrounds/forest_background.jpeg");

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
        Label titleLabel = new Label("Test next area", skin);
        TextButton nextAreaButton = new TextButton("Next Area", skin);

        // Add listeners to buttons
        nextAreaButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                // Change to the intro screen
                game.setScreen(new DesertScreen(game));
            }

        });

        // Add components to the table
        table.add(titleLabel).padBottom(20).row(); // Add titleLabel with padding and move to next row
        table.add(nextAreaButton).padTop(10); // Add nextAreaButton with padding at the top
    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        Gdx.input.setInputProcessor(stage);

        assetManager.load("Music/111 Secret of the Forest.mp3", Music.class);
        assetManager.finishLoading();
        introScreenMusic = assetManager.get("Music/111 Secret of the Forest.mp3", Music.class);
        introScreenMusic.setLooping(true);
        introScreenMusic.play();

        // Add a listener for skipping the intro
        stage.addListener(new ClickListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (keycode == Input.Keys.SPACE || keycode == Input.Keys.ENTER) {
                    skipIntro();
                    return true;
                }
                return false;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                skipIntro();
            }
        });

    }

    // Create a method to handle the skipping of the intro
    private void skipIntro() {
        introScreenMusic.stop(); // Stop the music
        game.setScreen(new CharacterSelectionScreen(game)); // Switch to the character selection screen
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
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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
        introScreenMusic.stop();
    }
    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        stage.dispose();
        skin.dispose();
        introScreenMusic.dispose();
    }

}
