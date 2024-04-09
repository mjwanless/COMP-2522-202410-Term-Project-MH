package ca.bcit.comp2522.termproject;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Character.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

/**
 * Represents the character selection screen of the game.
 */
public class CharacterSelectionScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private Stage stage;
    private Skin skin;
    private Character[] availableCharacters;
    private ArrayList<Character> selectedCharacters;
    private AssetManager assetManager;
    private Music characterSelectMusic;

    /**
     * Constructs a new CharacterSelectionScreen object.
     * @param game The main game object.
     */
    public CharacterSelectionScreen(final DiceGame game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.img = new Texture("backgrounds/CharacterSelectBackground.jpeg");
        this.stage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        this.availableCharacters = new Character[]{
                new Paladin(),
                new Rogue(),
                new Warrior(),
                new Wizard()
        };
        this.selectedCharacters = new ArrayList<>();
        this.assetManager = new AssetManager();
        setupUI();
    }

    /**
     * Sets up the user interface components.
     */
    private void setupUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Adjust the pad, spacing, and sizes as needed
        mainTable.defaults().pad(5).space(5);

        for (Character character : availableCharacters) {
            // Create a row table for character details
            Table rowTable = new Table();
            // Image
            Image characterImage = new Image(new Texture(Gdx.files.internal(character.getImagePath())));
            rowTable.add(characterImage).size(100).padRight(10); // Fixed size for the image

            // Name
            TextField nameTextField = new TextField(character.getName(), skin);
            nameTextField.setDisabled(true); // Disable editing of character names
            rowTable.add(nameTextField).width(200).padRight(10);

            // Stats
            Label statsLabel = new Label(character.getStatsAsString(), skin);
            statsLabel.setWrap(true); // Make sure the text wraps
            rowTable.add(statsLabel).width(250).pad(10).padLeft(20).padRight(20); // Padding on all sides with extra on left and right

            // Select button
            TextButton selectButton = new TextButton("Select", skin);
            selectButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (selectedCharacters.contains(character)) {
                        // Character is already selected, deselect it
                        selectedCharacters.remove(character);
                        selectButton.setText("Select");
                    } else {
                        // Character is not selected, select it
                        if (selectedCharacters.size() < 2) {
                            selectedCharacters.add(character);
                            selectButton.setText("Selected");
                        } else {
                            // Notify the user that only two characters can be selected
                            showCharacterSelectionErrorDialog();
                        }
                    }
                }
            });
            rowTable.add(selectButton).width(100).pad(10);

            // Add the row to the main table
            mainTable.add(rowTable).fillX().expandX().padBottom(10);
            mainTable.row(); // Start a new row for the next character
        }

        // Confirm button at the bottom of the table
        TextButton confirmButton = new TextButton("Confirm", skin);
        confirmButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (selectedCharacters.size() == 2) {
                    // Convert the ArrayList to an array and pass it to the Forest Screen
                    Character[] selectedCharsArray = selectedCharacters.toArray(new Character[0]);
                    game.setScreen(new ForestScreen(game, selectedCharsArray));
                } else {
                    // Notify the user that two characters need to be selected
                    showCharacterSelectionErrorDialog();
                }
            }
        });

        // Add the confirm button to the table, making it span all columns
        Table buttonTable = new Table();
        buttonTable.center().bottom().padBottom(20);
        buttonTable.add(confirmButton).pad(10);
        mainTable.add(buttonTable).expandX().center().bottom().colspan(4).padBottom(20);
    }

    /**
     * Shows a dialog indicating a character selection error.
     */
    private void showCharacterSelectionErrorDialog() {
        Dialog dialog = new Dialog("Selection Error", skin) {
            @Override
            protected void result(Object obj) {
                System.out.println("Closed dialog with result: " + obj);
            }
        };

        dialog.text("You must select two characters to proceed.");

        // Create the button with a smaller size.
        TextButton okButton = new TextButton("OK", skin);
        okButton.getLabel().setFontScale(0.8f); // Make the text smaller, adjust as needed
        dialog.button(okButton, true); // sends "true" as the result

        dialog.key(Input.Keys.ENTER, true); // sends "true" when the ENTER key is pressed
        dialog.show(stage);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        // Set up any initial settings or assets for character selection

        assetManager.load("Music/106 Guardia Millennial Fair.mp3", Music.class);
        assetManager.finishLoading();
        characterSelectMusic = assetManager.get("Music/106 Guardia Millennial Fair.mp3", Music.class);
        characterSelectMusic.setLooping(true);
        characterSelectMusic.play();
    }

    @Override
    public void render(float delta) {
        game.clearScreen();
        batch.begin();
        // Render the character selection background and UI elements
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

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
        characterSelectMusic.stop();
        // Dispose of any intro assets or elements
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        stage.dispose();
        skin.dispose();
        characterSelectMusic.dispose();
    }
}
