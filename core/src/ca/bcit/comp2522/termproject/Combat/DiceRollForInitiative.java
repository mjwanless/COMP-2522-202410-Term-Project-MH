package ca.bcit.comp2522.termproject.Combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;
import java.util.Random;

public class DiceRollForInitiative extends Actor {
    public enum TurnOrder {
        PLAYER,
        ENEMY
    }

    private Texture diceTexture;
    private TurnOrder turnOrder;
    private boolean isRolling; // Added to indicate if the dice is currently rolling
    private float rollTime; // Time the dice has been rolling

    public DiceRollForInitiative(List<Integer> diceResults) {
        setPosition(Gdx.graphics.getWidth() / 2f, Gdx.graphics.getHeight() / 2f);
        startRoll(); // Start rolling the dice
        setVisible(false);
    }

    public void startRoll() {
        setVisible(true);
        isRolling = true;
        rollTime = 0f;
        // Assuming you want the roll to last a certain amount of time
        // If you want a random roll time, you can randomize this value
    }

    @Override
    public void act(float delta) {
        if (isRolling) {
            rollTime += delta;
            if (rollTime > 1.0f) { // After 1 second, the dice roll ends
                completeRoll();
            }
        }
    }

    private void completeRoll() {
        Random random = new Random();
        turnOrder = random.nextBoolean() ? TurnOrder.PLAYER : TurnOrder.ENEMY;

        String fileName = (turnOrder == TurnOrder.PLAYER) ? "dice/32x/front-1.png" : "dice/32x/front-6.png";
        diceTexture = new Texture(Gdx.files.internal(fileName));
        isRolling = false; // The dice roll is finished
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (!isRolling && diceTexture != null) {
            batch.draw(diceTexture, getX() - diceTexture.getWidth() / 2f, getY() - diceTexture.getHeight() / 2f);
        }
    }

    public TurnOrder getTurnOrder() {
        return turnOrder;
    }

    // Consider adding a method to hide the dice again if needed
    public void hideDice() {
        setVisible(false);
    }

    public boolean isFinishedRolling() {
        return !isRolling;
    }

    public void dispose() {
        if (diceTexture != null) {
            diceTexture.dispose();
        }
    }
}