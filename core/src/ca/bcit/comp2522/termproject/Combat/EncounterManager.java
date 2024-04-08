package ca.bcit.comp2522.termproject.Combat;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;

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
    private SpriteBatch batch;
    private Texture[] diceTextures = new Texture[6]; // Textures for dice faces 1 to 6
    private boolean showDice = false;
    private int currentDiceIndex = 0;
    private float elapsedTime = 0; // To control dice rolling animation


    public EncounterManager(Stage stage, Runnable onEncounterEnd) {
        this.stage = stage;
        this.onEncounterEnd = onEncounterEnd;
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        this.batch = new SpriteBatch();
        loadDiceTextures();
        generateOverlay();
        initializeUI();
        this.numberOfDice = 8; // Default number of dice
        CombatManager.setEventListener(this);

    }

    private void loadDiceTextures() {
        for (int i = 0; i < 6; i++) {
            diceTextures[i] = new Texture(Gdx.files.internal("dice/32x/front-" + (i + 1) + ".png"));
        }
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

        // Display the corresponding dice faces
//        showDiceFaces(diceResults);
    }

//    private void showDiceFaces(List<Integer> diceResults) {
//        diceRoll = new DiceRollForInitiative(diceResults);
//        diceRoll.startRoll();
//        showDice = true;
//    }

    public void render(float delta) {
        if (showDice) {
            elapsedTime += delta;
            if (elapsedTime <= 2.0f) { // Continue rolling for 2 seconds
                batch.begin();
                currentDiceIndex = (currentDiceIndex + 1) % 6; // Loop through dice textures
                batch.draw(diceTextures[currentDiceIndex], Gdx.graphics.getWidth() / 2f - 16, Gdx.graphics.getHeight() / 2f - 16);
                batch.end();
            } else {
                endDiceRoll(); // Automatically stop the animation after 2 seconds
            }
        }
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
                CombatManager.switchTurn(numberOfDice);
            }
        });
        switchTurnButton.setVisible(false);
        CombatManager.initializeDiceRoller();

        stage.addActor(switchTurnButton);
        CombatManager.setCurrentInitiator(Initiative.PLAYER);
    }

//    public void clearDiceDisplay() {
//        showDice = false;
//        elapsedTime = 0;
//    }

    private void displayRandomInitiativeResult() {
        // Initiates the dice roll animation
        startDiceRoll();

        Initiative result = new Random().nextBoolean() ? Initiative.PLAYER : Initiative.ENEMY;
        // Simulate some delay before showing the result, to allow dice animation to play
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                resultLabel.setText(result + " rolls for initiative!");
                resultLabel.setVisible(true);
                endDiceRoll(); // Call to stop the dice rolling animation
                // Proceed with the rest of the game logic here
            }
        }, 2); // Delay for 2 seconds for the sake of the animation
    }

    private void startDiceRoll() {
        showDice = true;
        elapsedTime = 0; // Reset elapsed time for the animation
    }

    private void endDiceRoll() {
        showDice = false;
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

    }
    public void dispose() {
        for (Texture tex : diceTextures) {
            if (tex != null) tex.dispose();
        }
        if (batch != null) batch.dispose();
    }
}
