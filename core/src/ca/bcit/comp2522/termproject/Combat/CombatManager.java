package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.Random;

public class CombatManager {

    private Initiative currentInitiator;
    private CombatEventListener eventListener;
    private boolean enemyHasAttacked = false;
    private Character character;
    private Enemy enemy;
    private EntityManager entityManager;

    // Interface for listening to combat events
    public interface CombatEventListener {
        void onPlayerAttack(int dieResult);
        void onEnemyAttack();
    }

    // Constructor
    public CombatManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Initiative getCurrentInitiator() {
        return currentInitiator;
    }

    public void setEventListener(CombatEventListener listener) {
        this.eventListener = listener;
    }

    private int rollDie(int numberOfFaces) {
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    public void attack() {
        int numberOfFaces = 6; // Number of faces on the die (e.g., a six-sided die)
        int dieResult = rollDie(numberOfFaces); // Result of the die roll

        if (currentInitiator == Initiative.PLAYER) {
            System.out.println("Player attacks with a roll of: " + dieResult);
            if (eventListener != null) {
                eventListener.onPlayerAttack(dieResult); // Notify about the player attack
            }
            if (enemy != null) {
                entityManager.applyDamageToAllEnemies(dieResult); // Apply damage to the enemy
                System.out.println("Enemy health after attack: " + enemy.getHealth());
                if (enemy.getHealth() <= 0) {
                    // Additional logic if needed, e.g., remove enemy from game, award XP to player
                }
            }
        } else if (currentInitiator == Initiative.ENEMY && !enemyHasAttacked) {
            System.out.println("Enemy attacks with a roll of: " + dieResult);
            if (eventListener != null) {
                eventListener.onEnemyAttack();
            }
            if (character != null) {
                // Assuming Character has a method to apply damage
                // character.applyDamage(dieResult);
                // System.out.println("Character health after attack: " + character.getHealth());
            }
            enemyHasAttacked = true;
        }
    }

    public void handleCombatRound(Initiative initiator) {
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

    public void resetEnemyAttackFlag() {
        enemyHasAttacked = false;
    }

    public void setCurrentInitiator(Initiative initiator) {
        this.currentInitiator = initiator;
    }

    public void switchTurn() {
        if (currentInitiator == Initiative.PLAYER) {
            currentInitiator = Initiative.ENEMY;
        } else {
            currentInitiator = Initiative.PLAYER;
        }
        handleCombatRound(currentInitiator);
    }
}
