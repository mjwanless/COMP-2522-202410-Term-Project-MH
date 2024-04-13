package ca.bcit.comp2522.termproject;

import ca.bcit.comp2522.termproject.Character.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Screen displayed when the boss is defeated.
  * This screen displays a victory message and an exit button.
 * It also handles music playing and disposing of assets.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 * @version 2024
 */
public class BossKilledScreen implements Screen {

    private static final String BACKGROUND_IMAGE_PATH = "backgrounds/VictoryScreen.jpg";
    private static final String MUSIC_FILE_PATH = "Music/111 Secret of the Forest.mp3";
    private static final float MUSIC_VOLUME = 0.5f;
    private static final Color VICTORY_COLOR = Color.GOLD;
    private static final float VICTORY_LABEL_FONT_SCALE = 2f;
    private static final int EXIT_BUTTON_WIDTH = 200;
    private static final int EXIT_BUTTON_HEIGHT = 50;
    private static final String EXIT_BUTTON_TEXT = "Exit";

    private final DiceGame game;
    private SpriteBatch batch;
    private final Texture backgroundTexture;
    private ShapeRenderer shapeRenderer;
    private final AssetManager assetManager;
    private Music bossKilledScreenMusic;
    private final Character[] selectedCharacters;
    private final Stage stage;
    private final Skin skin;



    /**
     * Constructs a BossKilledScreen object.
     *
     * @param game              The game instance.
     * @param selectedCharacters The characters selected for the encounter.
     */
    public BossKilledScreen(final DiceGame game, final Character[] selectedCharacters) {
        this.game = game;
        this.selectedCharacters = selectedCharacters;
        batch = new SpriteBatch();
        backgroundTexture = new Texture(BACKGROUND_IMAGE_PATH);

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        assetManager = new AssetManager();
        setupUI();
    }

    private void setupUI() {
        final int bottomPad = 20;
        final int topPad = 20;

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Victory Label
        Label victoryLabel = new Label("VICTORY", skin, "default");
        victoryLabel.setFontScale(VICTORY_LABEL_FONT_SCALE);
        victoryLabel.setColor(VICTORY_COLOR);

        // Positioning the label at the top
        mainTable.add(victoryLabel).padBottom(bottomPad).row();

        // Exit Button
        TextButton exitButton = new TextButton(EXIT_BUTTON_TEXT, skin);
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                Gdx.app.exit();
            }
        });

        // Adding the exit button at the bottom of the table
        mainTable.add(exitButton).padTop(topPad).size(EXIT_BUTTON_WIDTH, EXIT_BUTTON_HEIGHT);

        mainTable.center();
    }

    /**
     *
     * Prepares any assets or variables for the introduction of the boss killed screen.
     * Initializes the sprite batch, shape renderer, and input processor for the stage.
     * Loads the boss killed screen music and starts playing it with the specified volume.
     */
    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);

        // Load and play music
        assetManager.load(MUSIC_FILE_PATH, Music.class);
        assetManager.finishLoading();
        bossKilledScreenMusic = assetManager.get(MUSIC_FILE_PATH, Music.class);
        bossKilledScreenMusic.setLooping(true);
        bossKilledScreenMusic.setVolume(MUSIC_VOLUME);
        bossKilledScreenMusic.play();
    }

    /**
     * Renders the boss killed screen.
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(final float delta) {
        final float actTime = 30f;
        game.clearScreen();

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / actTime));
        stage.draw();
    }


    @Override
    public void resize(final int width, final int height) {
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

    /**
     * Called when this screen is no longer the current screen for a {@link com.badlogic.gdx.Game}.
     * This method is called automatically when the screen is replaced by another screen or when
     * the application is closed. Stops the boss killed screen music.
     */
    @Override
    public void hide() {
        bossKilledScreenMusic.stop();
        // Implement if there is anything you need to hide
    }

    /**
     * Releases all resources of the boss killed screen.
     *
     * <p>Disposes of the sprite batch, shape renderer, background texture, and character textures.
     * Additionally, disposes of the boss killed screen music.</p>
     */
    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
        }
        if (shapeRenderer != null) {
            shapeRenderer.dispose();
        }
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }
        // Dispose character textures if not used elsewhere
        for (Character character : selectedCharacters) {
            character.dispose();
        }
        bossKilledScreenMusic.dispose();
    }

}
