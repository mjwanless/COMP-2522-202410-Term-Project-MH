package ca.bcit.comp2522.termproject;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Combat.EncounterManager;
import ca.bcit.comp2522.termproject.Combat.EnemyGeneration;
import ca.bcit.comp2522.termproject.Enemy.Enemy;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
/**
 * Represents the castle screen of the game.
 * This screen displays the castle background image, along with characters and enemies.
 * It also handles music playing and disposing of assets.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 * @version 2024
 */
public class CastleScreen implements Screen {

    // Constants for layout
    private static final float CHARACTER_INFO_START_X = 50; // X position for character info box
    private static final float CHARACTER_INFO_START_Y = Gdx.graphics.getHeight() - 450; // Y position from the top
    private static final float CHARACTER_INFO_BOX_WIDTH = 300; // Width of the character info box
    private static final float CHARACTER_INFO_BOX_HEIGHT = 100; // Height of the character info box
    private static final float PORTRAIT_SIZE = 90; // Size of the character portrait
    private static final float PADDING = 10; // Padding inside the info box
    private static final int ENEMY_COUNT = 2; // Number of enemies generated

    // Field declarations for various components used in the screen
    private final DiceGame game; // The main game object to interact with other game screens and functionalities
    private SpriteBatch batch; // Used for drawing 2D images, such as the background and characters
    private final Texture img; // The background image for the forest screen
    private ShapeRenderer shapeRenderer; // Used for drawing shapes (e.g., for UI or highlighting)
    private final AssetManager assetManager; // Manages and loads assets, like textures and sounds
    private Music castleScreenMusic; // Background music for the forest screen
    private Character[] selectedCharacters; // Array of characters selected by the player
    private final Stage stage; // A container for UI elements, handling their events and rendering
    private final Skin skin; // Defines the look and feel of UI elements
    private Enemy[] enemies; // Array of enemies present in the forest
    private final EncounterManager encounterManager; // Manages encounters with enemies

    private int defeatedEnemyCount = 0; // Counter for defeated enemies



    /**
     * Constructs a new CastleScreen object.
     *
     * @param game               The main game object.
     * @param selectedCharacters The characters selected for the encounter.
     */
    public CastleScreen(final DiceGame game, final Character[] selectedCharacters) {
        this.game = game;
        this.selectedCharacters = selectedCharacters;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/castle_background.jpeg");
        enemies = EnemyGeneration.generateEnemiesForLocation(Locations.CASTLE, ENEMY_COUNT).toArray(new Enemy[0]);

        stage = new Stage(new ScreenViewport());
        ca.bcit.comp2522.termproject.Combat.EntityManager entityManager
                = new ca.bcit.comp2522.termproject.Combat.EntityManager(selectedCharacters, enemies);

        encounterManager = new EncounterManager(stage, this::onEncounterEnd, entityManager);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        assetManager = new AssetManager();
        setupUI();
    }

    /**
     * Initializes and sets up UI components for the forest screen.
     */
    private void setupUI() {
        // Creating the main table to organize UI elements
        Table mainTable = new Table();
        mainTable.setFillParent(true); // The table will fill the entire stage
        stage.addActor(mainTable); // Adding the table to the stage

        // Setting default cell properties for the table

        // Additional UI elements like buttons can be added here
    }

    private void renderSelectedCharacters(final SpriteBatch batch, final ShapeRenderer shapeRenderer) {
        final int ypos = 20;

        // Font setup
        BitmapFont font = new BitmapFont(); // Use your existing BitmapFont here
        font.getData().setScale(1f); // Scale down the font size

        // Draw background for character info
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK); // Color for the background of the character info
        float startY = CHARACTER_INFO_START_Y;
        for (Character character : selectedCharacters) {
            shapeRenderer.rect(CHARACTER_INFO_START_X, startY, CHARACTER_INFO_BOX_WIDTH, CHARACTER_INFO_BOX_HEIGHT);
            startY -= (CHARACTER_INFO_BOX_HEIGHT + PADDING); // Move to the next character's position
        }
        shapeRenderer.end();

        // Draw images and text on top of the boxes
        batch.begin();
        startY = CHARACTER_INFO_START_Y; // Reset startY position
        for (Character character : selectedCharacters) {
            // Draw character portrait
            Texture portrait = character.getImage();
            batch.draw(portrait, CHARACTER_INFO_START_X + PADDING,
                    startY + (CHARACTER_INFO_BOX_HEIGHT - PORTRAIT_SIZE) / 2, PORTRAIT_SIZE, PORTRAIT_SIZE);

            // Position text
            float textX = CHARACTER_INFO_START_X + PORTRAIT_SIZE + 2 * PADDING;
            float textY = startY + CHARACTER_INFO_BOX_HEIGHT - PADDING;

            // Draw character's name
            font.setColor(Color.WHITE); // Set font color to white
            font.draw(batch, character.getName(), textX, textY);

            // Draw character's health
            String healthString = "HP: " + character.getHealth();
            font.draw(batch, healthString, textX, textY - ypos); // Adjust Y position for health below the name

            // Move to the next character's position
            startY -= (CHARACTER_INFO_BOX_HEIGHT + PADDING);
        }
        batch.end();

        // Dispose font to avoid memory leaks
        font.dispose();
    }

    private void renderEnemies(final SpriteBatch batch, final ShapeRenderer shapeRenderer) {

        // Constants for layout
        float startX = Gdx.graphics.getWidth() - 350; // Adjusted X position
        float startY = Gdx.graphics.getHeight() - 450;
        float boxWidth = 300;
        float boxHeight = 100;
        float portraitSize = 90;
        float padding = 10;

        // Font setup
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1f);

        // Draw background for enemy info
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK); // Background color for enemy info
        for (Enemy enemy : enemies) {
            shapeRenderer.rect(startX, startY, boxWidth, boxHeight);
            startY -= (boxHeight + padding); // Adjust startY for the next enemy's position
        }
        shapeRenderer.end();

        batch.begin();
        startY = Gdx.graphics.getHeight() - 450; // Reset startY for drawing images and text
        for (Enemy enemy : enemies) {
            // Draw enemy portrait
            Texture portrait = new Texture(Gdx.files.internal(enemy.getImagePath()));
            batch.draw(portrait, startX + padding, startY + (boxHeight - portraitSize) / 2, portraitSize, portraitSize);

            // Position text
            float textX = startX + portraitSize + 2 * padding;
            float textY = startY + boxHeight - padding;

            // Draw enemy's name
            font.setColor(Color.WHITE);
            font.draw(batch, enemy.getName(), textX, textY);

            // Draw enemy's health
            String healthString = "HP: " + enemy.getHealth();
            font.draw(batch, healthString, textX, textY - 20);

            // Move to the next enemy
            startY -= (boxHeight + padding);
        }
        batch.end();

        font.dispose(); // Dispose font if it's not reused elsewhere
    }

    // Method called when the EncounterManager is done
    private void onEncounterEnd() {
        // Indicates whether an encounter/battle is currently active
        boolean encounterActive = false; // Set the flag to false indicating the encounter is over
    }


    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);

        // Load and play music
        assetManager.load("Music/305 Zeal Palace.mp3", Music.class);
        assetManager.finishLoading();
        castleScreenMusic = assetManager.get("Music/305 Zeal Palace.mp3", Music.class);
        castleScreenMusic.setLooping(true);
        castleScreenMusic.play();

        encounterManager.startOverlay();
    }


    @Override
    public void render(final float delta) {
        // Clear screen
        game.clearScreen();

        // Draw background
        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Render characters and enemies
        renderSelectedCharacters(batch, shapeRenderer);
        renderEnemies(batch, shapeRenderer);

        // Update and draw stage
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        // Check for defeated characters and enemies and remove them from the stage
        for (Character character : selectedCharacters) {
            if (character.getHealth() <= 0) {
                selectedCharacters = removeCharacter(character, selectedCharacters);
                if (selectedCharacters.length == 0) {
                    game.setScreen(new CharacterDeathScreen(game)); // Change to the appropriate screen
                }
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() <= 0) {
                enemies = removeEnemy(enemy, enemies);
                defeatedEnemyCount++;
                if (defeatedEnemyCount == 2) {
                    switchToRestScreen();
                }
            }
        }
    }

    // Method to remove a character from the array
    private Character[] removeCharacter(final Character characterToRemove, final Character[] characters) {
        Character[] updatedCharacters = new Character[characters.length - 1];
        int idx = 0;
        for (Character character : characters) {
            if (character != characterToRemove) {
                updatedCharacters[idx++] = character;
            }
        }
        return updatedCharacters;
    }
    private Enemy[] removeEnemy(final Enemy enemyToRemove, final Enemy[] enemies) {
        Enemy[] updatedEnemies = new Enemy[enemies.length - 1];
        int idx = 0;
        for (Enemy enemy : enemies) {
            if (enemy != enemyToRemove) {
                updatedEnemies[idx++] = enemy;
            }
        }
        return updatedEnemies;
    }
    /**
     * Switches the turn button functionality to go to the rest screen.
     * This method removes the reroll button and switch turn button, increments the combat counter,
     * and adds a new button for resting. When the resting button is clicked, it changes the screen
     * to the appropriate BossKilledScreen.
     */
    public void switchToRestScreen() {
        // Dispose of the reroll button and switch turn button
        encounterManager.removeRerollButton();
        encounterManager.removeSwitchTurnButton();
        game.combatCounter += 1;

        // Define constants for button properties
        final float buttonWidth = 200;
        final float buttonHeight = 100;
        final float buttonPositionX = (Gdx.graphics.getWidth() - buttonWidth) / 2;
        final float buttonPositionY = 20;

        // Create a new button for resting
        TextButton restButton = new TextButton("Next", skin);
        restButton.setSize(buttonWidth, buttonHeight);
        restButton.setPosition(buttonPositionX, buttonPositionY);
        restButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                game.setScreen(new BossKilledScreen(game, selectedCharacters)); // Change to the appropriate screen
            }
        });
        stage.addActor(restButton); // Add the new button to the stage
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
     * Called when this screen is no longer the current screen for a {@link Game}.
     * Stops the music playing in the background and disposes of any resources used by the screen.
     */
    @Override
    public void hide() {
        // Stop music when hiding screen
        castleScreenMusic.stop();
        // Implement if there is anything you need to hide
    }

    /**
     * Releases all resources of the screen.
     * Disposes of the sprite batch, shape renderer, background image texture, characters, enemies, music, and stage.
     */
    @Override
    public void dispose() {
        // Dispose resources
        batch.dispose(); // Dispose sprite batch
        shapeRenderer.dispose(); // Dispose shape renderer
        img.dispose(); // Dispose background image texture
        for (Character character : selectedCharacters) { // Dispose characters
            character.dispose();
        }
        castleScreenMusic.dispose(); // Dispose background music
        for (Enemy enemy : enemies) { // Dispose enemies
            enemy.dispose();
        }
        stage.dispose(); // Dispose stage
    }

}
