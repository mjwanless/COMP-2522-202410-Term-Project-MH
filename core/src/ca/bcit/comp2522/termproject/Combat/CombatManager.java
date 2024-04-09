package ca.bcit.comp2522.termproject.Combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CombatManager {

    private static Initiative currentInitiator;
    private static CombatEventListener eventListener;

    public interface CombatEventListener {
        void onPlayerAttack(int numberOfDice, List<Integer> diceResults);
        void onEnemyAttack();
    }

    public static void setEventListener(CombatEventListener listener) {
        eventListener = listener;
    }

    private static int rollDie(int numberOfFaces) {
        Random random = new Random();
        return random.nextInt(numberOfFaces) + 1; // Generate a random number between 1 and numberOfFaces
    }

    private static void playerAttack() {
        int numberOfFaces = 6; // Specify the number of faces on the die (e.g., a six-sided die)
        int dieResult = rollDie(numberOfFaces);
        List<Integer> diceResults = new ArrayList<>();
        diceResults.add(dieResult);
        if (eventListener != null) {
            eventListener.onPlayerAttack(1, diceResults);
        }
    }

    private static void enemyAttack() {
        if (eventListener != null) {
            eventListener.onEnemyAttack();
        }
    }

    public static void handleCombatRound(Initiative initiator) {
        switch (initiator) {
            case PLAYER:
                System.out.println("Player Attacks!");
                playerAttack();
                break;
            case ENEMY:
                System.out.println("Enemy Attacks!");
                enemyAttack();
                break;
            default:
                System.out.println("Invalid initiator!");
        }
    }

    public static void setCurrentInitiator(Initiative initiator) {
        currentInitiator = initiator;
    }

    public static void switchTurn() {
        currentInitiator = (currentInitiator == Initiative.PLAYER) ? Initiative.ENEMY : Initiative.PLAYER;
        handleCombatRound(currentInitiator);
    }
}
