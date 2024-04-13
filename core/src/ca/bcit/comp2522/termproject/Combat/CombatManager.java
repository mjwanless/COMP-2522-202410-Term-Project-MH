package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.List;
import java.util.Random;

/**
 * Manages combat between characters and enemies in the game.
 * This class handles the flow of combat, including initiating attacks, determining the current attacker,
 * and notifying listeners about combat events. *
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 * @version 2024
 *
 */
public class CombatManager {

    private Initiative currentInitiator;
    private CombatEventListener eventListener;
    private boolean enemyHasAttacked = false;
    private final List<Character> characters;
    private final List<Enemy> enemies;
    private final EntityManager entityManager;

    /**
     * Interface for listening to combat events.
     */
    public interface CombatEventListener {
        /**
         * Invoked when the player attacks.
         *
         * @param dieResult the result of the die roll for the attack.
         */
        void onPlayerAttack(int dieResult);

        /**
         * Invoked when an enemy attacks.
         *
         * @param dieResult the result of the die roll for the attack.
         */
        void onEnemyAttack(int dieResult);
    }

    /**
     * Constructs a new CombatManager with the specified EntityManager.
     *
     * @param entityManager the EntityManager to use for accessing characters and enemies.
     */
    public CombatManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.characters = entityManager.characters;
        this.enemies = entityManager.enemies;
    }

    /**
     * Gets the current initiator of combat.
     *
     * @return the current initiator.
     */
    public Initiative getCurrentInitiator() {
        return currentInitiator;
    }

    /**
     * Sets the CombatEventListener for receiving combat events.
     *
     * @param listener the CombatEventListener to set.
     */
    public void setEventListener(final CombatEventListener listener) {
        this.eventListener = listener;
    }

    /**
     * Rolls a die with the specified number of faces.
     *
     * @return the result of the die roll.
     */
    private int rollDie() {
        final int numberOfFaces = 6;
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    /**
     * Initiates an attack in combat.
     */
    public void attack() {
        int dieResult = rollDie(); // Result of the die roll

        if (currentInitiator == Initiative.PLAYER) {
            if (eventListener != null) {
                eventListener.onPlayerAttack(dieResult); // Notify about the player attack
            }
            if (enemies != null) {
                entityManager.applyDamageToAllEnemies(dieResult); // Apply damage to all enemies
            }
        } else if (currentInitiator == Initiative.ENEMY && !enemyHasAttacked) {
            System.out.println("Enemy attacks with a roll of: " + dieResult);
            if (eventListener != null) {
                eventListener.onEnemyAttack(dieResult);
            }
            if (characters != null) {
                entityManager.applyDamageToAllCharacters(dieResult);
            }
            enemyHasAttacked = true;
        }
    }

    /**
     * Handles a combat round with the specified initiator.
     *
     * @param initiator the initiator of the combat round.
     */
    public void handleCombatRound(final Initiative initiator) {
        setCurrentInitiator(initiator);
        switch (initiator) {
            case PLAYER:
                System.out.println("Player Attacks!");
                attack();
                resetEnemyAttackFlag();
                break;
            case ENEMY:
                if (!enemyHasAttacked) {
                    System.out.println("Enemy Attacks!");
                    attack();
                }
                break;
            default:
                System.out.println("Invalid initiator!");
        }
    }

    /**
     * Resets the flag indicating whether an enemy has attacked.
     */
    public void resetEnemyAttackFlag() {
        enemyHasAttacked = false;
    }

    /**
     * Sets the current initiator of combat.
     *
     * @param initiator the current initiator to set.
     */
    public void setCurrentInitiator(final Initiative initiator) {
        this.currentInitiator = initiator;
    }

    /**
     * Switches the turn in combat.
     */
    public void switchTurn() {
        if (currentInitiator == Initiative.PLAYER) {
            currentInitiator = Initiative.ENEMY;
        } else {
            currentInitiator = Initiative.PLAYER;
        }
        handleCombatRound(currentInitiator);
    }

    /**
     * Enumerates the possible initiators of combat.
     */
    public enum Initiative {
        /**
         * Represents the player as the initiator of combat.
         */
        PLAYER,
        /**
         * Represents an enemy as the initiator of combat.
         */
        ENEMY
    }
}
