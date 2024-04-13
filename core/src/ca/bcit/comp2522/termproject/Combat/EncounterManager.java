package ca.bcit.comp2522.termproject.Combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

/**
 * Manages encounters in the game, including UI display and interaction.
 * This class handles the overlay, UI elements, and event handling during encounters.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 *
 */
public class EncounterManager implements CombatManager.CombatEventListener {

    private static final int MAX_REROLL_LIMIT = 2;
    private static final float OVERLAY_ALPHA = 0.5f;
    private static final int REROLL_BUTTON_Y_OFFSET = 135;
    private static final int INITIATIVE_BUTTON_WIDTH = 500;
    private static final int TURN_BUTTON_WIDTH = 350;
    private static final int REROLL_BUTTON_WIDTH = 350;
    private static final int DIALOG_PADDING = 75;

    private final Stage stage;
    private Image darkBackground;
    private Label encounterMessage;
    private Label resultLabel;
    private Label turnLabel;
    private final Runnable onEncounterEnd;
    private final Skin skin;
    private TextButton rollInitiativeButton;
    private TextButton switchTurnButton;
    private TextButton rerollButton;
    private final CombatManager combatManager;
    private int rerollCount = 0;

    /**
     * Constructs an EncounterManager with the specified stage, callback, and entity manager.
     *
     * @param stage the stage to add UI elements to.
     * @param onEncounterEnd the callback to invoke when the encounter ends.
     * @param entityManager the entity manager for the game.
     */
    public EncounterManager(final Stage stage, final Runnable onEncounterEnd, final EntityManager entityManager) {
        this.stage = stage;
        this.onEncounterEnd = onEncounterEnd;
        this.skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
        this.combatManager = new CombatManager(entityManager);
        this.combatManager.setEventListener(this);
        generateOverlay();
        initializeUI();
    }

    /**
     * Displays the dice result on the UI.
     *
     * @param dieResult the result of the dice roll.
     */
    public void displayDiceResult(final int dieResult) {
        String resultText = "Dice Result: " + dieResult;
        resultLabel.setText(resultText);
        resultLabel.setVisible(true);
    }

    /**
     * Displays the player's attack message on the UI.
     */
    public void displayPlayerResult() {
        String resultText = "Player Attacks";
        turnLabel.setText(resultText);
        turnLabel.setVisible(true);
    }

    /**
     * Displays the enemy's attack message on the UI.
     */
    public void displayEnemyResult() {
        String resultText = "Enemy Attacks";
        turnLabel.setText(resultText);
        turnLabel.setVisible(true);
    }

    /**
     * Generates the dark overlay for the encounter UI.
     */
    private void generateOverlay() {
        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0, 0, 0, OVERLAY_ALPHA));
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();

        darkBackground = new Image(texture);
        darkBackground.setVisible(false);
        stage.addActor(darkBackground);
    }

    /**
     * Initializes the UI elements for the encounter.
     */
    private void initializeUI() {
        final int horizPos = 100;
        final int vertPos = 50;
        final int posValue = 20;
        final int heightMod = 3;


        encounterMessage = new Label("An enemy appears!", skin, "default");
        encounterMessage.setPosition((float) Gdx.graphics.getWidth() / 2 - encounterMessage.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 2 - encounterMessage.getHeight() / 2);
        encounterMessage.setVisible(false);
        stage.addActor(encounterMessage);

        turnLabel = new Label("", skin, "default");
        turnLabel.setPosition((float) Gdx.graphics.getWidth() / 2 - horizPos,
                (float) Gdx.graphics.getHeight() / heightMod + vertPos);
        turnLabel.setVisible(false);
        stage.addActor(turnLabel);

        resultLabel = new Label("", skin, "default");
        resultLabel.setPosition((float) Gdx.graphics.getWidth() / 2 - horizPos,
                (float) Gdx.graphics.getHeight() / heightMod + horizPos);
        resultLabel.setVisible(false);
        stage.addActor(resultLabel);

        rollInitiativeButton = new TextButton("Roll for initiative", skin);
        rollInitiativeButton.setSize(INITIATIVE_BUTTON_WIDTH, horizPos);
        rollInitiativeButton.setPosition((Gdx.graphics.getWidth() - rollInitiativeButton.getWidth()) / 2, posValue);
        rollInitiativeButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                displayRandomInitiativeResult();
                endOverlay();
            }
        });
        rollInitiativeButton.setVisible(false);
        stage.addActor(rollInitiativeButton);

        switchTurnButton = new TextButton("Switch Turn", skin);
        switchTurnButton.setSize(TURN_BUTTON_WIDTH, horizPos);
        switchTurnButton.setPosition((Gdx.graphics.getWidth() - switchTurnButton.getWidth()) / 2, posValue);
        switchTurnButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                combatManager.switchTurn();
            }
        });
        switchTurnButton.setVisible(false);

        stage.addActor(switchTurnButton);
        combatManager.setCurrentInitiator(CombatManager.Initiative.PLAYER);

        rerollButton = new TextButton("Reroll Dice", skin);
        rerollButton.setSize(REROLL_BUTTON_WIDTH, horizPos);
        rerollButton.setPosition((Gdx.graphics.getWidth() - rerollButton.getWidth()) / 2, horizPos);
        rerollButton.addListener(new ClickListener() {
            @Override
            public void clicked(final InputEvent event, final float x, final float y) {
                // Call the method to re-roll the dice
                rerollDice();
            }
        });
        rerollButton.setVisible(false);
        stage.addActor(rerollButton);

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.dispose();
    }

    /**
     * Displays the result of the random initiative roll on the UI.
     */
    private void displayRandomInitiativeResult() {
        CombatManager.Initiative result;
        if (new Random().nextBoolean()) {
            result = CombatManager.Initiative.PLAYER;
        } else {
            result = CombatManager.Initiative.ENEMY;
        }

        resultLabel.setText(result + " rolls for initiative!");
        resultLabel.setVisible(true);

        // If the enemy rolls initiative, immediately trigger the enemy's attack
        if (result == CombatManager.Initiative.ENEMY) {
            combatManager.handleCombatRound(CombatManager.Initiative.ENEMY);
        } else {
            combatManager.handleCombatRound(CombatManager.Initiative.PLAYER);
        }
        // Proceed with the rest of the game logic here if needed
    }

    /**
     * Shows or hides the reroll button based on the current initiator.
     *
     * @param show true to show the reroll button, false to hide it.
     */
    public void showRerollButton(final boolean show) {
        if (combatManager.getCurrentInitiator() == CombatManager.Initiative.PLAYER) {
            rerollButton.setVisible(show);
            rerollButton.setPosition((Gdx.graphics.getWidth() - rerollButton.getWidth()) / 2, REROLL_BUTTON_Y_OFFSET);
        } else {
            rerollButton.setVisible(false);
        }
    }

    /**
     * Shows the reroll error dialog when the reroll limit is reached.
     */
    private void showRerollErrorDialog() {
        final float fontScale = 0.8f;

        Dialog dialog = new Dialog("Re-roll Limit Reached", skin) {
            @Override
            protected void result(final Object obj) {
                System.out.println("Closed dialog with result: " + obj);
            }
        };

        dialog.text("You can only re-roll twice during combat.").pad(DIALOG_PADDING);
        dialog.getContentTable().padTop(DIALOG_PADDING); // Add padding at the top of the content table
        dialog.getContentTable().row().padTop(DIALOG_PADDING); // Add space between the text and the button below it

        TextButton okButton = new TextButton("OK", skin);
        okButton.getLabel().setFontScale(fontScale);
        dialog.button(okButton, true);

        dialog.key(Input.Keys.ENTER, true);
        dialog.show(stage);
    }

    /**
     * Handles the rerolling of dice during combat.
     * Limits the number of rerolls to twice per combat.
     */
    private void rerollDice() {
        if (rerollCount < MAX_REROLL_LIMIT) { // Check if the reroll limit has not been reached
            rerollCount++; // Increment the reroll count
            // Call CombatManager to re-roll the dice
            combatManager.attack();
        } else {
            showRerollErrorDialog(); // Show the reroll error dialog
        }
    }

    /**
     * Removes the reroll button from the UI.
     */
    public void removeRerollButton() {
        rerollButton.remove();
    }

    /**
     * Removes the switch turn button from the UI.
     */
    public void removeSwitchTurnButton() {
        switchTurnButton.remove();
    }

    /**
     * Starts the encounter overlay, displaying encounter messages and UI elements.
     */
    public void startOverlay() {
        darkBackground.setVisible(true);
        encounterMessage.setVisible(true);
        rollInitiativeButton.setVisible(true);
        switchTurnButton.setVisible(false);
        resultLabel.setVisible(false);
    }

    /**
     * Ends the encounter overlay, hiding encounter messages and UI elements.
     */
    public void endOverlay() {
        darkBackground.setVisible(false);
        encounterMessage.setVisible(false);
        rollInitiativeButton.setVisible(false);
        switchTurnButton.setVisible(true);
        if (onEncounterEnd != null) {
            onEncounterEnd.run();
        }
    }

    /**
     * Displays the player's attack message on the UI and the result of the dice roll.
     *
     * @param dieResult the result of the player's dice roll.
     */
    @Override
    public void onPlayerAttack(final int dieResult) {
        displayPlayerResult();
        displayDiceResult(dieResult);
        showRerollButton(true);
    }

    /**
     * Displays the enemy's attack message on the UI and the result of the dice roll.
     *
     * @param dieResult the result of the enemy's dice roll.
     */
    @Override
    public void onEnemyAttack(final int dieResult) {
        displayEnemyResult();
        displayDiceResult(dieResult);
        showRerollButton(false);
    }

}
