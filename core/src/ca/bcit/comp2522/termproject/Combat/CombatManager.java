package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatManager {

    private static Initiative currentInitiator;
    private static CombatEventListener eventListener;
    private static boolean enemyHasAttacked = false;
    private static Character character;
    private static Enemy enemy;


    // Interface for listening to combat events
    public interface CombatEventListener {
        void onPlayerAttack(int numberOfDice, List<Integer> diceResults);
        void onEnemyAttack();
    }

    public static Initiative getCurrentInitiator() {
        return currentInitiator;
    }

    // Set the combat event listener
    public static void setEventListener(CombatEventListener listener) {
        eventListener = listener;
    }

    public static void setCharacter(Character character) {
        CombatManager.character = character;
    }

    public static void setEnemy(Enemy enemy) {
        CombatManager.enemy = enemy;
    }

    // Roll a die with the specified number of faces
    private static int rollDie(int numberOfFaces) {
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    public static void attack() {
        int numberOfFaces = 6; // Number of faces on the die (e.g., a six-sided die)
        int dieResult = rollDie(numberOfFaces); // Result of the die roll
        List<Integer> diceResults = new ArrayList<>();
        diceResults.add(dieResult);

        if (currentInitiator == Initiative.PLAYER) {
            // If the player is the attacker
            System.out.println("Player attacks with a roll of: " + dieResult);
            if (eventListener != null) {
                eventListener.onPlayerAttack(1, diceResults);
            }
            // Assuming 'enemy' is not null, apply damage to the enemy
            if (enemy != null) {
//                enemy.applyDamage(dieResult);
                System.out.println("Enemy health after attack: " + enemy.getHealth());
            }
        } else if (currentInitiator == Initiative.ENEMY && !enemyHasAttacked) {
            // If the enemy is the attacker
            System.out.println("Enemy attacks with a roll of: " + dieResult);
            if (eventListener != null) {
                eventListener.onEnemyAttack(); // This method needs to exist in your CombatEventListener interface
            }
            // Assuming 'character' is not null, apply damage to the player's character
            if (character != null) {
//                character.applyDamage(dieResult);
//                System.out.println("Character health after attack: " + character.getHealth());
            }
            enemyHasAttacked = true; // Mark that the enemy has attacked this turn
        }
    }

    public static void handleCombatRound(Initiative initiator) {
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
                    enemyHasAttacked = true;
                }
                break;
            default:
                System.out.println("Invalid initiator!");
        }
    }

    // Add a method to reset the flag after the player's turn
    public static void resetEnemyAttackFlag() {
        enemyHasAttacked = false;
    }

    // Set the current initiator
    public static void setCurrentInitiator(Initiative initiator) {
        currentInitiator = initiator;
    }

    // Switch the turn between player and enemy
    public static void switchTurn() {
        if (currentInitiator == Initiative.PLAYER) {
            currentInitiator = Initiative.ENEMY;
        } else {
            currentInitiator = Initiative.PLAYER;
        }
        handleCombatRound(currentInitiator);
    }
}
