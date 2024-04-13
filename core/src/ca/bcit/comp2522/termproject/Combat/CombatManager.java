package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.List;
import java.util.Random;

/**
 * Manages combat actions between player characters and enemies.
 * This class handles attacks, turn management, and event handling during combat scenarios.
 *
 * <p>
 * The combat manager keeps track of the current initiator of the combat action, allowing
 * for alternating turns between the player and enemy entities.
 * </p>
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 * @version 2024
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
     * The implementing class must provide methods for handling player and enemy attacks.
     */
    public interface CombatEventListener {
        /**
         * Invoked when a player attack occurs.
         *
         * @param dieResult the result of the die roll for the attack.
         */
        void onPlayerAttack(int dieResult);

        /**
         * Invoked when an enemy attack occurs.
         *
         * @param dieResult the result of the die roll for the attack.
         */
        void onEnemyAttack(int dieResult);
    }

    /**
     * Constructs a CombatManager with the specified entity manager.
     *
     * @param entityManager the entity manager for the game.
     */
    public CombatManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
        this.characters = entityManager.characters;
        this.enemies = entityManager.enemies;
    }

    /**
     * Gets the current initiator of the combat action.
     *
     * @return the current initiator.
     */
    public Initiative getCurrentInitiator() {
        return currentInitiator;
    }

    /**
     * Sets the event listener for combat events.
     *
     * @param listener the event listener to set.
     */
    public void setEventListener(final CombatEventListener listener) {
        this.eventListener = listener;
    }

    private int rollDie() {
        final int numberOfFaces = 6; // Number of faces on the die (e.g., a six-sided die)
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    /**
     * Initiates an attack action during combat.
     * Determines the initiator of the attack and applies damage accordingly.
     */
    public void attack() {
        final int dieResult = rollDie(); // Result of the die roll

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
     * The combat round includes attack actions and turn management.
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
     * Resets the flag indicating whether the enemy has attacked during the current combat round.
     */
    public void resetEnemyAttackFlag() {
        enemyHasAttacked = false;
    }

    /**
     * Sets the current initiator of the combat action.
     *
     * @param initiator the current initiator to set.
     */
    public void setCurrentInitiator(final Initiative initiator) {
        this.currentInitiator = initiator;
    }

    /**
     * Switches the turn to the next combat initiator and handles the combat round accordingly.
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
     * Enumerates the possible initiators of a combat action within the game.
     * This enum is used to determine which party (player or enemy) is currently
     * taking an action in a combat scenario.
     */
    public enum Initiative {
        PLAYER, ENEMY
    }
}
