package ca.bcit.comp2522.termproject.Combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatManager {

    private static Initiative currentInitiator;
    private static CombatEventListener eventListener;
    private static boolean enemyHasAttacked = false;


    // Interface for listening to combat events
    public interface CombatEventListener {
        void onPlayerAttack(int numberOfDice, List<Integer> diceResults);
        void onEnemyAttack();
    }

    // Set the combat event listener
    public static void setEventListener(CombatEventListener listener) {
        eventListener = listener;
    }

    // Roll a die with the specified number of faces
    private static int rollDie(int numberOfFaces) {
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    // Perform a player attack
    public static void playerAttack() {
        int numberOfFaces = 6; // Specify the number of faces on the die (e.g., a six-sided die)
        int dieResult = rollDie(numberOfFaces);
        List<Integer> diceResults = new ArrayList<>();
        diceResults.add(dieResult);
        if (eventListener != null) {
            eventListener.onPlayerAttack(1, diceResults);
        }
    }

    // Perform an enemy attack
    private static void enemyAttack() {
        if (eventListener != null) {
            eventListener.onEnemyAttack();
        }
    }

    // Modify the handleCombatRound method to set the flag accordingly
    public static void handleCombatRound(Initiative initiator) {
        switch (initiator) {
            case PLAYER:
                System.out.println("Player Attacks!");
                playerAttack();
                break;
            case ENEMY:
                if (!enemyHasAttacked) {
                    System.out.println("Enemy Attacks!");
                    enemyAttack();
                    enemyHasAttacked = true; // Set the flag to true after attacking
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
