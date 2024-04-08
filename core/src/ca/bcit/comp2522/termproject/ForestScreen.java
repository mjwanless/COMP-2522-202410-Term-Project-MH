package ca.bcit.comp2522.termproject;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Combat.DiceRollForInitiative;
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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class ForestScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;
    private Music forestScreenMusic;
    private Character[] selectedCharacters;
    private Stage stage;
    private Skin skin;
    private Enemy[] enemies;
    private EncounterManager encounterManager;
    private DiceRollForInitiative diceRollForInitiative;
    private TextureRegion diceTexture;
    private boolean diceRollCompleted;
    private boolean encounterActive = true;

    public ForestScreen(final DiceGame game, final Character[] selectedCharacters) {
        this.game = game;
        this.selectedCharacters = selectedCharacters;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/forest_background.jpeg");

        // Load the dice textures for the animation
        stage = new Stage(new ScreenViewport());
        encounterManager = new EncounterManager(stage, this::onEncounterEnd);
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        diceRollForInitiative = new DiceRollForInitiative();
        diceRollForInitiative.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);

        assetManager = new AssetManager();
        setupUI();
    }


    private void setupUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Adjust the pad, spacing, and sizes as needed
        mainTable.defaults().pad(5).space(5);

        TextButton goToDesertButton = new TextButton("Go to Desert", skin);
        goToDesertButton.setSize(450, 100); // Set the size of the button
        // Position the button at the bottom center of the screen
        goToDesertButton.setPosition((Gdx.graphics.getWidth() - goToDesertButton.getWidth()) / 2, 100); // Raise it 20px above the bottom

        goToDesertButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                game.setScreen(new DesertScreen(game, selectedCharacters)); // Transition to DesertScreen
            }
        });

        stage.addActor(goToDesertButton);
    }

    private void renderSelectedCharacters(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        float startX = 50; // X position for character info box
        float startY = Gdx.graphics.getHeight() - 450; // Y position from the top of the screen
        float boxWidth = 300; // Width of the character info box
        float boxHeight = 100; // Height of the character info box
        float portraitSize = 90; // Size of the character portrait
        float padding = 10; // Padding inside the info box

        BitmapFont font = new BitmapFont(); // Use your existing BitmapFont here
        font.getData().setScale(1f); // Scale down the font size

        // Draw the encapsulating rectangle for character info
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK); // Color for the background of the character info

        for (Character character : selectedCharacters) {
            // Draw the background rectangle
            shapeRenderer.rect(startX, startY, boxWidth, boxHeight);

            // Move to the next character's position
            startY -= (boxHeight + padding);
        }

        shapeRenderer.end();

        // Now draw the images and text on top of the boxes
        batch.begin();

        // Reset startY position
        startY = Gdx.graphics.getHeight() - 450;

        for (Character character : selectedCharacters) {
            // Draw the character portrait within the box
            Texture portrait = character.getImage();
            batch.draw(portrait, startX + padding, startY + (boxHeight - portraitSize) / 2, portraitSize, portraitSize);

            // Position the text next to the portrait inside the box
            float textX = startX + portraitSize + 2 * padding;
            float textY = startY + boxHeight - padding;

            // Draw the character's name
            font.setColor(Color.WHITE); // Set font color to white for better visibility
            font.draw(batch, character.getName(), textX, textY);

            // Draw the character's health
            // Assuming getHealthString method exists in your Character class
            String healthString = "HP: " + character.getHealth();
            font.draw(batch, healthString, textX, textY - 20); // Adjust Y position for health below the name

            // Move to the next character's position
            startY -= (boxHeight + padding);
        }

        batch.end();

        // Don't forget to dispose of the font when you're done to avoid memory leaks
        font.dispose();
    }

    private void renderEnemies(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        // Adjust the start X position to place enemies on the right side
        float startX = Gdx.graphics.getWidth() - 350; // Adjusted X position
        float startY = Gdx.graphics.getHeight() - 450;
        float boxWidth = 300;
        float boxHeight = 100;
        float portraitSize = 90;
        float padding = 10;

        BitmapFont font = new BitmapFont(); // Consider moving font initialization outside if it doesn't change
        font.getData().setScale(1f);

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
            // If you have a method to get the texture from the enemy, use it here
            // Make sure textures are preloaded or cached to avoid reloading every frame
            Texture portrait = new Texture(Gdx.files.internal(enemy.getImagePath())); // Example method, ensure you implement this in your Enemy class or preload textures

            batch.draw(portrait, startX + padding, startY + (boxHeight - portraitSize) / 2, portraitSize, portraitSize);

            float textX = startX + portraitSize + 2 * padding;
            float textY = startY + boxHeight - padding;

            font.setColor(Color.WHITE);
            font.draw(batch, enemy.getName(), textX, textY);

            String healthString = "HP: " + enemy.getHealth(); // Assume method exists or enemy has a field for health
            font.draw(batch, healthString, textX, textY - 20);

            startY -= (boxHeight + padding); // Adjust startY for the next enemy
        }

        batch.end();

        font.dispose(); // Dispose font if it's not reused elsewhere
        // Note: Texture disposal should be handled where the Texture is loaded or managed
    }

    // This method is called when the EncounterManager is done.
    private void onEncounterEnd() {
        // Now, we assume the encounter has ended and it's time for the dice roll.
        // You can start the dice roll animation here or set a flag to start it in the render method.
        diceRollForInitiative.startRoll(); // Start the dice roll.
        encounterActive = false; // Set the flag to false indicating the encounter is over.
    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);
        enemies = EnemyGeneration.generateEnemiesForLocation(Locations.FOREST, 2).toArray(new Enemy[0]); // Example: Generate 2 enemies for the forest

        // Load and play music
        assetManager.load("Music/111 Secret of the Forest.mp3", Music.class);
        assetManager.finishLoading();
        forestScreenMusic = assetManager.get("Music/111 Secret of the Forest.mp3", Music.class);
        forestScreenMusic.setLooping(true);
        forestScreenMusic.play();

        // Initialize the dice roll actor
        diceRollForInitiative = new DiceRollForInitiative();
        diceRollForInitiative.setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f); // Center on the screen
        stage.addActor(diceRollForInitiative); // Add to stage for processing and drawing

        encounterManager.startEncounter();
    }

    @Override
    public void render(float delta) {
        game.clearScreen();

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Check if the encounter has ended and the dice roll is completed.
        if (!encounterActive && !diceRollForInitiative.isFinishedRolling()) {
            // If the encounter is over but the dice roll is not yet completed, render the dice.
            renderDice(batch, delta);
        }

        // Your existing code
        renderSelectedCharacters(batch, shapeRenderer);
        renderEnemies(batch, shapeRenderer);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    private void renderDice(SpriteBatch batch, float delta) {
        batch.begin();

        // Update and draw the dice roll actor
        diceRollForInitiative.act(delta); // Update the actor based on time
        diceRollForInitiative.draw(batch, 1); // Draw the actor with maximum alpha

        batch.end();
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
        forestScreenMusic.stop();
        // Implement if there is anything you need to hide
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        img.dispose();
        // Dispose character textures if not used elsewhere
        for (Character character : selectedCharacters) {
            character.dispose();
        }
        forestScreenMusic.dispose();
        for(Enemy enemy : enemies) {
            enemy.dispose();
        }
        if (diceTexture != null) {
            diceTexture.getTexture().dispose();
        }
        stage.dispose();
    }
}