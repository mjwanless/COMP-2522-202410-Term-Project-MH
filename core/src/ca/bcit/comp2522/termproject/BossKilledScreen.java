package ca.bcit.comp2522.termproject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ca.bcit.comp2522.termproject.Character.Character;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class BossKilledScreen implements Screen {

    private final DiceGame game;
    private SpriteBatch batch;
    private Texture img;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;
    private Music desertScreenMusic;
    private Character[] selectedCharacters;


    public BossKilledScreen(final DiceGame game, Character[] selectedCharacters) {
        this.game = game;
        this.selectedCharacters = selectedCharacters;
        batch = new SpriteBatch();
        img = new Texture("backgrounds/VictoryScreen.jpeg");
        assetManager = new AssetManager();
        // Load and play music
        assetManager.load("Music/201 Ruined World.mp3", Music.class);
        assetManager.finishLoading();
        desertScreenMusic = assetManager.get("Music/201 Ruined World.mp3", Music.class);
        desertScreenMusic.setLooping(true);
        desertScreenMusic.play();
    }

    @Override
    public void show() {
        // Prepare any assets or variables for the intro
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

    }

    @Override
    public void render(float delta) {
        game.clearScreen();

        batch.begin();
        batch.draw(img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();


        renderSelectedCharacters(batch, shapeRenderer);
        // Handle additional rendering or logic here
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
    }
}
