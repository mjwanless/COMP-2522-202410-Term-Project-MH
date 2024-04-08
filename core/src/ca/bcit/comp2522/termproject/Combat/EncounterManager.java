package ca.bcit.comp2522.termproject.Combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

// Assuming you have an Initiative enum somewhere
enum Initiative {
    PLAYER, ENEMY;
}

public class EncounterManager {

    private Stage stage;
    private Image darkBackground;
    private Label encounterMessage, resultLabel; // Added resultLabel here
    private Runnable onEncounterEnd;
    private Skin skin;
    private TextButton rollInitiativeButton;

    public EncounterManager(Stage stage, Runnable onEncounterEnd) {
        this.stage = stage;
        this.onEncounterEnd = onEncounterEnd;
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        generateOverlay();
        initializeUI();
    }

    private void generateOverlay() {
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, 0.5f));
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        darkBackground = new Image(texture);
        darkBackground.setVisible(false);
        stage.addActor(darkBackground);
    }

    private void initializeUI() {
        encounterMessage = new Label("An enemy appears!", skin, "default");
        encounterMessage.setPosition(Gdx.graphics.getWidth() / 2 - encounterMessage.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - encounterMessage.getHeight() / 2);
        encounterMessage.setVisible(false);
        stage.addActor(encounterMessage);

        // Initialize resultLabel
        resultLabel = new Label("", skin, "default");
        resultLabel.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 3);
        resultLabel.setVisible(false);
        stage.addActor(resultLabel);

        // Initialize and setup the Roll for Initiative button
        rollInitiativeButton = new TextButton("Roll for initiative", skin);
        rollInitiativeButton.setSize(200, 50);
        rollInitiativeButton.setPosition((Gdx.graphics.getWidth() - rollInitiativeButton.getWidth()) / 2, 20);
        rollInitiativeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                displayRandomInitiativeResult();
                // endOverlay(); // Uncomment if ending overlay here
            }
        });
        rollInitiativeButton.setVisible(false);
        stage.addActor(rollInitiativeButton);
    }

    private void displayRandomInitiativeResult() {
        Initiative result = new Random().nextBoolean() ? Initiative.PLAYER : Initiative.ENEMY;
        resultLabel.setText(result + " rolls for initiative!");
        resultLabel.setVisible(true);
        // Update the label's position if needed, depending on the new text's size
    }

    public void startOverlay() {
        darkBackground.setVisible(true);
        encounterMessage.setVisible(true);
        rollInitiativeButton.setVisible(true);
        resultLabel.setVisible(false); // Ensure resultLabel is hidden at the start
    }

    public void endOverlay() {
        darkBackground.setVisible(false);
        encounterMessage.setVisible(false);
        rollInitiativeButton.setVisible(false);
        // Optionally, decide if you want to hide resultLabel here
        if (onEncounterEnd != null) {
            onEncounterEnd.run();
        }
    }
    // Additional methods unchanged
}
