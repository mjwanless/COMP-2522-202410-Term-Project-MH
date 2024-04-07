package ca.bcit.comp2522.termproject.Combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class DiceRollForInitiative extends Actor {
    // Define the enum inside the class
    public enum TurnOrder {
        PLAYER,
        ENEMY
    }

    private Animation<TextureRegion> diceAnimation;
    private float stateTime;
    private TurnOrder turnOrder;
    private boolean finishedRolling;

    public DiceRollForInitiative() {
        // Assuming you have packed all your dice images into a TextureAtlas
        TextureAtlas diceAtlas = new TextureAtlas(Gdx.files.internal("dice/dice.atlas"));
        diceAnimation = new Animation<>(0.1f, diceAtlas.findRegions("front"), Animation.PlayMode.LOOP);
        rollDice(); // Starts the dice roll
    }

    public void rollDice() {
        Random random = new Random();
        turnOrder = random.nextBoolean() ? TurnOrder.PLAYER : TurnOrder.ENEMY;
        // The rolling will be shown for a set amount of time, say 1 second
        finishedRolling = false;
        stateTime = 0;
    }

    @Override
    public void act(float delta) {
        if (!finishedRolling) {
            stateTime += delta;
            if (stateTime > 1.0f) { // This is how long the dice rolls before stopping
                finishedRolling = true;
                // Here we would set the dice to the final side based on turnOrder
                // For simplicity, let's just say player = 1, enemy = 6
                int finalIndex = turnOrder == TurnOrder.PLAYER ? 0 : 5; // Adjust if your frames are indexed differently
                TextureRegion finalFrame = diceAnimation.getKeyFrames()[finalIndex];
                diceAnimation = new Animation<>(0f, finalFrame);
            }
        }
    }

    @Override
    public void draw(Batch batch, float alpha) {
        if (diceAnimation != null && !finishedRolling) {
            TextureRegion frame = diceAnimation.getKeyFrame(stateTime);
            batch.draw(frame, getX(), getY(), getOriginX(), getOriginY(),
                    frame.getRegionWidth(), frame.getRegionHeight(),
                    getScaleX(), getScaleY(), getRotation());
        }
    }

    public TurnOrder getTurnOrder() {
        return turnOrder;
    }

    public boolean isFinishedRolling() {
        return finishedRolling;
    }
}
