package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.List;
import java.util.Random;

/**
 * Manages combat interactions between characters and enemies.
 * This class handles turn management, attack resolution, and event handling.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
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
         * Handles the event when the player attacks.
         *
         * @param dieResult the result of the die roll.
         */
        void onPlayerAttack(int dieResult);

        /**
         * Handles the event when the enemy attacks.
         *
         * @param dieResult the result of the die roll.
         */
        void onEnemyAttack(int dieResult);
    }

    /**
     * Constructs a CombatManager with the specified EntityManager.
     *
     * @param entityManager the EntityManager instance.
     */
    public CombatManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.characters = entityManager.characters;
        this.enemies = entityManager.enemies;
    }

    /**
     * Gets the current initiator of combat.
     *
     * @return the current Initiative value.
     */
    public Initiative getCurrentInitiator() {
        return currentInitiator;
    }

    /**
     * Sets the event listener for combat events.
     *
     * @param listener the CombatEventListener instance.
     */
    public void setEventListener(final CombatEventListener listener) {
        this.eventListener = listener;
    }

    private int rollDie(final int numberOfFaces) {
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    /**
     * Initiates an attack during combat.
     */
    public void attack() {
        final int numberOfFaces = 6; // Number of faces on the die (e.g., a six-sided die)
        int dieResult = rollDie(numberOfFaces); // Result of the die roll

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
     * Handles a combat round initiated by the specified initiator.
     *
     * @param initiator the Initiative value representing the initiator.
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
     * Resets the flag indicating if the enemy has attacked.
     */
    public void resetEnemyAttackFlag() {
        enemyHasAttacked = false;
    }

    /**
     * Sets the current initiator of combat.
     *
     * @param initiator the Initiative value representing the initiator.
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

}
