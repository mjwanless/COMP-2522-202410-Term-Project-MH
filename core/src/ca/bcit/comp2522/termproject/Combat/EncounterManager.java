package ca.bcit.comp2522.termproject.Combat;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class EncounterManager {

    private Stage stage;
    private Image darkBackground;
    private Label encounterMessage;
    private Runnable onEncounterEnd;
    private Skin skin;

    public EncounterManager(Stage stage, Runnable onEncounterEnd) {
        this.stage = stage;
        this.onEncounterEnd = onEncounterEnd;
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        initializeUI();
    }

    private void initializeUI() {
        // Darken Background
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, 0.5f)); // Semi-transparent black
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        darkBackground = new Image(texture);
        stage.addActor(darkBackground);

        // Display Message
        LabelStyle labelStyle = new LabelStyle();
        labelStyle.font = new BitmapFont(); // Use your own font
        labelStyle.fontColor = Color.WHITE;

        // Use the skin for the Label
        encounterMessage = new Label("An enemy appears!", skin,"default");
        encounterMessage.setPosition(Gdx.graphics.getWidth() / 2 - encounterMessage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - encounterMessage.getHeight() / 2);
        stage.addActor(encounterMessage);

// Tap to Proceed
        stage.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                encounterMessage.remove();
                darkBackground.remove();
                stage.removeListener(this);
                if (onEncounterEnd != null) {
                    onEncounterEnd.run();
                }
                return true;
            }
        });
    }

    public void startEncounter() {
        // Show encounter UI components
        darkBackground.setVisible(true);
        encounterMessage.setVisible(true);
    }

    public void endEncounter() {
        // Hide encounter UI components and invoke the callback
        darkBackground.setVisible(false);
        encounterMessage.setVisible(false);
        if (onEncounterEnd != null) {
            onEncounterEnd.run();
        }
    }
    // Additional methods like showing characters and enemies can be added here
}