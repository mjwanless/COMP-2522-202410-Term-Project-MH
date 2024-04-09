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
    PLAYER, ENEMY
}

public class EncounterManager implements CombatManager.CombatEventListener {

    private Stage stage;
    private Image darkBackground;
    private Label encounterMessage, resultLabel;
    private Runnable onEncounterEnd;
    private Skin skin;
    private TextButton rollInitiativeButton, switchTurnButton;

    // Constructor
    public EncounterManager(Stage stage, Runnable onEncounterEnd) {
        this.stage = stage;
        this.onEncounterEnd = onEncounterEnd;
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        generateOverlay();
        initializeUI();
        CombatManager.setEventListener(this);
    }

    // Display dice results method
    public void displayDiceResult(final int dieResult) {
        String resultText = "Dice Result: " + dieResult;
        resultLabel.setText(resultText);
        resultLabel.setVisible(true);
    }

    // Generate overlay method
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

    // Initialize UI method
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

    // Display random initiative result method
    private void displayRandomInitiativeResult() {
        Initiative result = new Random().nextBoolean() ? Initiative.PLAYER : Initiative.ENEMY;
        resultLabel.setText(result + " rolls for initiative!");
        resultLabel.setVisible(true);

        // If the enemy rolls initiative, immediately trigger the enemy's attack
        if (result == Initiative.ENEMY) {
            CombatManager.handleCombatRound(Initiative.ENEMY);
        } else {
            CombatManager.handleCombatRound(Initiative.PLAYER);
        }
        // Proceed with the rest of the game logic here if needed
    }

    // Start overlay method
    public void startOverlay() {
        darkBackground.setVisible(true);
        encounterMessage.setVisible(true);
        rollInitiativeButton.setVisible(true);
        switchTurnButton.setVisible(false);
        resultLabel.setVisible(false);
    }

    // End overlay method
    public void endOverlay() {
        darkBackground.setVisible(false);
        encounterMessage.setVisible(false);
        rollInitiativeButton.setVisible(false);
        switchTurnButton.setVisible(true);
        if (onEncounterEnd != null) {
            onEncounterEnd.run();
        }
    }

    // Combat event listener methods
    @Override
    public void onPlayerAttack(int numberOfDice, List<Integer> diceResults) {
        // Since we're only rolling one die, we can assume diceResults contains only one value
        if (!diceResults.isEmpty()) {
            int dieResult = diceResults.get(0);
            displayDiceResult(dieResult);
        }
    }

    @Override
    public void onEnemyAttack() {
        // No implementation needed for player's encounter manager
    }
}
