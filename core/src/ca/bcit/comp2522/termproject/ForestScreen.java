package ca.bcit.comp2522.termproject;

// Importing necessary classes from the libGDX framework and the game's own packages

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Combat.EncounterManager;
import ca.bcit.comp2522.termproject.Combat.EnemyGeneration;
import ca.bcit.comp2522.termproject.Enemy.Enemy;
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
 * The ForestScreen class implements the game screen for the forest area.
 * It is responsible for rendering the forest area and managing interactions within it.
 */
public class ForestScreen implements Screen {

    // Field declarations for various components used in the screen
    private final DiceGame game; // The main game object to interact with other game screens and functionalities
    private SpriteBatch batch; // Used for drawing 2D images, such as the background and characters
    private final Texture img; // The background image for the forest screen
    private ShapeRenderer shapeRenderer; // Used for drawing shapes (e.g., for UI or highlighting)
    private final AssetManager assetManager; // Manages and loads assets, like textures and sounds
    private Music forestScreenMusic; // Background music for the forest screen
    private Character[] selectedCharacters; // Array of characters selected by the player
    private final Stage stage; // A container for UI elements, handling their events and rendering
    private final Skin skin; // Defines the look and feel of UI elements
    private Enemy[] enemies; // Array of enemies present in the forest
    private final EncounterManager encounterManager; // Manages encounters with enemies

    private int defeatedEnemyCount = 0; // Counter for defeated enemies

    /**
     * Constructor for the ForestScreen. Initializes the screen with the game context and selected characters.
     *
     * @param game               The main game object, providing context and access to global functionalities.
     * @param selectedCharacters An array of characters that have been selected by the player.
     */
    public ForestScreen(final DiceGame game, final Character[] selectedCharacters) {
        this.game = game;
        this.selectedCharacters = selectedCharacters;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/forest_background.jpeg");
        enemies = EnemyGeneration.generateEnemiesForLocation(Locations.FOREST, 2).toArray(new Enemy[0]);

        stage = new Stage(new ScreenViewport());
        ca.bcit.comp2522.termproject.Combat.EntityManager entityManager
                = new ca.bcit.comp2522.termproject.Combat.EntityManager(selectedCharacters, enemies);

        encounterManager = new EncounterManager(stage, this::onEncounterEnd, entityManager);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));


        assetManager = new AssetManager();
        setupUI(); // Calls the method to set up UI components for the screen
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
        mainTable.defaults().pad(5).space(5);
        // Additional UI elements like buttons can be added here
    }

    private void renderSelectedCharacters(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        // Constants for layout
        float startX = 50; // X position for character info box
        float startY = Gdx.graphics.getHeight() - 450; // Y position from the top of the screen
        float boxWidth = 300; // Width of the character info box
        float boxHeight = 100; // Height of the character info box
        float portraitSize = 90; // Size of the character portrait
        float padding = 10; // Padding inside the info box

        // Font setup
        BitmapFont font = new BitmapFont(); // Use your existing BitmapFont here
        font.getData().setScale(1f); // Scale down the font size

        // Draw background for character info
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK); // Color for the background of the character info
        for (Character character : selectedCharacters) {
            shapeRenderer.rect(startX, startY, boxWidth, boxHeight); // Draw the background rectangle
            startY -= (boxHeight + padding); // Move to the next character's position
        }
        shapeRenderer.end();

        // Draw images and text on top of the boxes
        batch.begin();
        startY = Gdx.graphics.getHeight() - 450; // Reset startY position
        for (Character character : selectedCharacters) {
            // Draw character portrait
            Texture portrait = character.getImage();
            batch.draw(portrait, startX + padding, startY + (boxHeight - portraitSize) / 2, portraitSize, portraitSize);

            // Position text
            float textX = startX + portraitSize + 2 * padding;
            float textY = startY + boxHeight - padding;

            // Draw character's name
            font.setColor(Color.WHITE); // Set font color to white
            font.draw(batch, character.getName(), textX, textY);

            // Draw character's health
            String healthString = "HP: " + character.getHealth();
            font.draw(batch, healthString, textX, textY - 20); // Adjust Y position for health below the name

            // Move to the next character's position
            startY -= (boxHeight + padding);
        }
        batch.end();

        // Dispose font to avoid memory leaks
        font.dispose();
    }

    private void renderEnemies(SpriteBatch batch, ShapeRenderer shapeRenderer) {
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
        // Initialization
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);

        // Load and play music
        assetManager.load("Music/111 Secret of the Forest.mp3", Music.class);
        assetManager.finishLoading();
        forestScreenMusic = assetManager.get("Music/111 Secret of the Forest.mp3", Music.class);
        forestScreenMusic.setLooping(true);
        forestScreenMusic.play();

        encounterManager.startOverlay();
    }

    @Override
    public void render(float delta) {
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
    private Character[] removeCharacter(Character characterToRemove, Character[] characters) {
        Character[] updatedCharacters = new Character[characters.length - 1];
        int idx = 0;
        for (Character character : characters) {
            if (character != characterToRemove) {
                updatedCharacters[idx++] = character;
            }
        }
        return updatedCharacters;
    }

    // Method to remove an enemy from the array
    private Enemy[] removeEnemy(Enemy enemyToRemove, Enemy[] enemies) {
        Enemy[] updatedEnemies = new Enemy[enemies.length - 1];
        int idx = 0;
        for (Enemy enemy : enemies) {
            if (enemy != enemyToRemove) {
                updatedEnemies[idx++] = enemy;
            }
        }
        return updatedEnemies;
    }

    // Method to switch the turn button functionality to go to rest screen
    /**
     * Switches the turn button functionality to go to the rest screen.
     */
    public void switchToRestScreen() {
        // Dispose of the reroll button and switch turn button
        encounterManager.removeRerollButton();
        encounterManager.removeSwitchTurnButton();
        game.combatCounter += 1;

        // Create a new button for resting
        TextButton restButton = new TextButton("Rest", skin);
        restButton.setSize(200, 100);
        restButton.setPosition((Gdx.graphics.getWidth() - restButton.getWidth()) / 2, 20);
        restButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    game.setScreen(new OptionsAndSaveExitScreen(game, selectedCharacters, Locations.DESERT));
            }
        });
        stage.addActor(restButton); // Add the new button to the stage
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
        // Stop music when hiding screen
        forestScreenMusic.stop();
        // Implement if there is anything you need to hide
    }

    @Override
    public void dispose() {
        // Dispose resources
        batch.dispose();
        shapeRenderer.dispose();
        img.dispose();
        for (Character character : selectedCharacters) {
            character.dispose();
        }
        forestScreenMusic.dispose();
        for (Enemy enemy : enemies) {
            enemy.dispose();

        }
        stage.dispose();
    }
}