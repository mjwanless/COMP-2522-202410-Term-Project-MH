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

import java.util.List;
import java.util.Random;

enum Initiative {
    PLAYER, ENEMY;
}

public class EncounterManager implements CombatManager.CombatEventListener {

    private Stage stage;
    private Image darkBackground;
    private Label encounterMessage, resultLabel;
    private Runnable onEncounterEnd;
    private Skin skin;
    private TextButton rollInitiativeButton, switchTurnButton;
    private int numberOfDice;

    public EncounterManager(Stage stage, Runnable onEncounterEnd) {
        this.stage = stage;
        this.onEncounterEnd = onEncounterEnd;
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        generateOverlay();
        initializeUI();
        this.numberOfDice = 8; // Default number of dice
        CombatManager.setEventListener(this);

    }

    public void displayDiceResults(List<Integer> diceResults) {
        // Display dice results on the resultLabel
        StringBuilder stringBuilder = new StringBuilder("Dice Results: ");
        for (Integer result : diceResults) {
            stringBuilder.append(result).append(", ");
        }
        String resultsText = stringBuilder.toString();
        resultLabel.setText(resultsText);
        resultLabel.setVisible(true);
    }

    public void render(float delta) {
        // No dice animation to render
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

        resultLabel = new Label("", skin, "default");
        resultLabel.setPosition(Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 3);
        resultLabel.setVisible(false);
        stage.addActor(resultLabel);

        rollInitiativeButton = new TextButton("Roll for initiative", skin);
        rollInitiativeButton.setSize(200, 50);
        rollInitiativeButton.setPosition((Gdx.graphics.getWidth() - rollInitiativeButton.getWidth()) / 2, 20);
        rollInitiativeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                displayRandomInitiativeResult();
                endOverlay();
            }
        });
        rollInitiativeButton.setVisible(false);
        stage.addActor(rollInitiativeButton);

        switchTurnButton = new TextButton("Switch Turn", skin);
        switchTurnButton.setSize(200, 50);
        switchTurnButton.setPosition((Gdx.graphics.getWidth() - switchTurnButton.getWidth()) / 2, 20);
        switchTurnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                CombatManager.switchTurn();
            }
        });
        switchTurnButton.setVisible(false);

        stage.addActor(switchTurnButton);
        CombatManager.setCurrentInitiator(Initiative.PLAYER);
    }

    private void displayRandomInitiativeResult() {
        Initiative result = new Random().nextBoolean() ? Initiative.PLAYER : Initiative.ENEMY;
        resultLabel.setText(result + " rolls for initiative!");
        resultLabel.setVisible(true);
        // Proceed with the rest of the game logic here
    }

    public void startOverlay() {
        darkBackground.setVisible(true);
        encounterMessage.setVisible(true);
        rollInitiativeButton.setVisible(true);
        switchTurnButton.setVisible(false);
        resultLabel.setVisible(false);
    }

    public void endOverlay() {
        darkBackground.setVisible(false);
        encounterMessage.setVisible(false);
        rollInitiativeButton.setVisible(false);
        switchTurnButton.setVisible(true);
        if (onEncounterEnd != null) {
            onEncounterEnd.run();
        }
    }

    @Override
    public void onPlayerAttack(int numberOfDice, List<Integer> diceResults) {
        // Display the dice results on the screen
        displayDiceResults(diceResults);
    }

    @Override
    public void onEnemyAttack() {
        // No implementation needed for player's encounter manager
    }

    public void dispose() {
        // No need to dispose of anything
    }
}
