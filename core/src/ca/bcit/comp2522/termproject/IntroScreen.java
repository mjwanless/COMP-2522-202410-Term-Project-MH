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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Represents the introduction screen of the game.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class IntroScreen implements Screen {

    private final DiceGame game;
    private final SpriteBatch batch;
    private final Texture img;
    private final AssetManager assetManager;
    private Music introScreenMusic;
    private final Stage stage;
    private final Skin skin;

    /**
     * Constructs a new IntroScreen object.
     * @param game The main game object.
     */
    public IntroScreen(final DiceGame game) {
        this.game = game;
        batch = new SpriteBatch();
        // Load an intro image or animation
        img = new Texture("backgrounds/IntroBackground.jpg");

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

        // The story text
        String storyText = "Six years have passed since the First War between man and orc.\n\n" +
                "The once mighty army of Azeroth lay among the blackened and charred remains of Stormwind Keep. " +
                "Those that escaped fled across the Great Sea, bringing tales of the suffering they had faced at the " +
                "hands of the Orcish Hordes.\n\n" +
                "Eager to engage in battle once again, the Orcs constructed ships of war to bear them across the " +
                "Great Sea.\n\n" +
                "The Orcish warriors yearned for the sounds of battle to fill the air and looked to the far horizon " +
                "for new blood to spill. Using the weapons forged by their new allies, the Humans made haste to " +
                "prepare " +
                "for the onslaught. " +
                "While Dwarven cannon were being loaded, others armed themselves with Elven steel and mail.\n\n" +
                "Now, united in arms with new allies against a common foe, Mankind stands at the shores of destiny " +
                "and awaits the coming of the Tides of Darkness.";

        // Create UI components
        Label titleLabel = new Label(storyText, skin);
        titleLabel.setWrap(true);
        titleLabel.setAlignment(Align.center);
        TextButton nextAreaButton = new TextButton("Character select", skin);

        // Create a ScrollPane for the story label
        ScrollPane scrollPane = new ScrollPane(titleLabel, skin);
        scrollPane.setScrollingDisabled(true, false); // Disable horizontal scrolling
        scrollPane.setFadeScrollBars(false);

        // Create a window for the story text
        Window storyWindow = new Window("Story", skin);
        storyWindow.add(scrollPane).prefWidth(Gdx.graphics.getWidth() * 0.8f)
                .prefHeight(Gdx.graphics.getHeight() * 0.6f);
        storyWindow.row();
        storyWindow.pack();

        // Position the story window in the center of the screen
        storyWindow.setPosition((float) com.badlogic.gdx.Gdx.graphics.getWidth() / 2 - storyWindow.getWidth() / 2,
                (float) com.badlogic.gdx.Gdx.graphics.getHeight() / 2 - storyWindow.getHeight() / 2);

        // Add listeners to buttons
        nextAreaButton.addListener(new ClickListener() {
            @Override
            public void clicked(final com.badlogic.gdx.scenes.scene2d.InputEvent event, final float x, final float y) {
                // Change to the intro screen
                game.setScreen(new CharacterSelectionScreen(game));
            }

        });

        // Center titleLabel horizontally and start from the vertical middle
        float initialYPosition = -(Gdx.graphics.getHeight() / 2f + titleLabel.getHeight() / 2f); // Center vertically
        float initialXPosition = (Gdx.graphics.getWidth() - titleLabel.getWidth()) / 2; // Center horizontally
        titleLabel.setPosition(initialXPosition, initialYPosition); // Set initial position for animation

        titleLabel.addAction(Actions.sequence(
                Actions.moveTo(initialXPosition, initialYPosition),
                Actions.delay(1f), // Wait for 1 second before scrolling
                Actions.moveTo(initialXPosition, titleLabel.getHeight() - 450, 8f) // Animate to move up
        ));

        // Due to manual positioning, we won't add titleLabel to the table
        stage.addActor(titleLabel); // Add directly to the stage instead

        // Position the button at the bottom of the screen
        table.row(); // Skip a row to move the button to the bottom
        table.add(nextAreaButton).expand().bottom().padBottom(50);
    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        Gdx.input.setInputProcessor(stage);

        assetManager.load("Music/222 Magus' Castle.mp3", Music.class);
        assetManager.finishLoading();
        introScreenMusic = assetManager.get("Music/222 Magus' Castle.mp3", Music.class);
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
        stage.getViewport().update(width, height, true);
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
