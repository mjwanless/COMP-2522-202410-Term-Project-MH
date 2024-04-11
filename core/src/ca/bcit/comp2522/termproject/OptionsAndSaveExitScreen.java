package ca.bcit.comp2522.termproject;

import ca.bcit.comp2522.termproject.Character.Character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class OptionsAndSaveExitScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;
    private Music optionsScreenMusic;
    private Character[] selectedCharacters;
    private Stage stage;
    private Skin skin;
    private Locations currentLocation;

    public OptionsAndSaveExitScreen(final DiceGame game, final Character[] selectedCharacters, Locations currentLocation) {
        this.game = game;
        this.selectedCharacters = selectedCharacters;
        this.currentLocation = currentLocation;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/campfire_background.jpeg");

        stage = new Stage(new ScreenViewport());
        skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));

        assetManager = new AssetManager();
        setupUI();
    }


    private void setupUI() {
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        // Adjust the pad, spacing, and sizes as needed
        mainTable.defaults().pad(5).space(5);
        // Create a button for healing characters
        TextButton healCharactersButton = new TextButton("Heal Characters", skin);
        healCharactersButton.setSize(300, 100); // Adjust size as needed
        healCharactersButton.setPosition(100, Gdx.graphics.getHeight() - 150); // Adjust position as needed

        healCharactersButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                healCharacters(); // Call the method to heal characters
            }
        });

        stage.addActor(healCharactersButton);

        if (currentLocation == Locations.FOREST) {
            TextButton goToForestButton = new TextButton("Go to Forest", skin);
            goToForestButton.setSize(750, 100); // Set the size of the button
            goToForestButton.setPosition((Gdx.graphics.getWidth() - goToForestButton.getWidth()) / 2, 100); // Raise it 20px above the bottom

            goToForestButton.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    game.setScreen(new ForestScreen(game, selectedCharacters)); // Transition to DesertScreen
                }
            });

            stage.addActor(goToForestButton);
        } else if (currentLocation == Locations.DESERT || currentLocation == Locations.VOLCANO) {
            TextButton goToNewLocationButton = new TextButton("Go to Next Location", skin);
            goToNewLocationButton.setSize(750, 100); // Set the size of the button
            goToNewLocationButton.setPosition((Gdx.graphics.getWidth() - goToNewLocationButton.getWidth()) / 2, 100); // Raise it 20px above the bottom

            goToNewLocationButton.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    // Determine the next location randomly
                    Locations nextLocation;
                    if (Math.random() < 0.5) {
                        game.setScreen(new DesertScreen(game, selectedCharacters));
                    } else {
                        game.setScreen(new DesertScreen(game, selectedCharacters));
                    }
                }
            });
            stage.addActor(goToNewLocationButton);
        } else if (currentLocation == Locations.CASTLE) {
            TextButton goToCastleButton = new TextButton("Go to Castle", skin);
            goToCastleButton.setSize(750, 100); // Set the size of the button
            // Position the button at the bottom center of the screen
            goToCastleButton.setPosition((Gdx.graphics.getWidth() - goToCastleButton.getWidth()) / 2, 100); // Raise it 20px above the bottom

            goToCastleButton.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    game.setScreen(new CastleScreen(game, selectedCharacters)); // Transition to DesertScreen
                }
            });

            stage.addActor(goToCastleButton);
        }

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
        optionsScreenMusic = assetManager.get("Music/111 Secret of the Forest.mp3", Music.class);
        optionsScreenMusic.setLooping(true);
        optionsScreenMusic.play();
    }

    @Override
    public void render(float delta) {
        game.clearScreen();

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();


        renderSelectedCharacters(batch, shapeRenderer);
        // Handle additional rendering or logic here

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    /**
     * Heals all selected characters by a specified amount.
     */
    private void healCharacters() {
        int healAmount = 20; // The amount of health to restore to each character.
        for (Character character : selectedCharacters) {
            character.heal(healAmount); // Assuming a heal method exists in your Character class.
        }
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
        optionsScreenMusic.stop();
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
        // Dispose character textures if not used elsewhere
        for (Character character : selectedCharacters) {
            character.dispose();
        }
        optionsScreenMusic.dispose();
        stage.dispose();
    }
}
