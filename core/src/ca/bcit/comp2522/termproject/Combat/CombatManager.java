package ca.bcit.comp2522.termproject.Combat;
import java.util.List;

public class CombatManager {

    private static Initiative currentInitiator;
    private static RollMultipleDice rollMultipleDice;
    private static CombatEventListener eventListener;

    public interface CombatEventListener {
        void onPlayerAttack(int numberOfDice, List<Integer> diceResults);
        void onEnemyAttack();
    }
    public static void initializeDiceRoller() {
        rollMultipleDice = new RollMultipleDice();
    }

    public static void setEventListener(CombatEventListener listener) {
        eventListener = listener;
    }

    private static void playerAttack(int numberOfDice) {
        List<Integer> diceResults = rollDice(numberOfDice);
        if (eventListener != null) {
            eventListener.onPlayerAttack(numberOfDice, diceResults);
        }
    }

    private static void enemyAttack() {
        if (eventListener != null) {
            eventListener.onEnemyAttack();
        }
    }

    public static void handleCombatRound(Initiative initiator, int numberOfDice) {
        switch (initiator) {
            case PLAYER:
                playerAttack(numberOfDice);
                break;
            case ENEMY:
                enemyAttack();
                break;
            default:
                System.out.println("Invalid initiator!");
        }
    }

    public static void setCurrentInitiator(Initiative initiator) {
        currentInitiator = initiator;
    }

    public static void switchTurn(int numberOfDice) {
        currentInitiator = (currentInitiator == Initiative.PLAYER) ? Initiative.ENEMY : Initiative.PLAYER;
        handleCombatRound(currentInitiator, numberOfDice);
    }

    // Method to roll multiple dice using RollMultipleDice class
    private static List<Integer> rollDice(int numberOfDice) {
        List<Integer> diceResults = rollMultipleDice.rollDice(numberOfDice, 6); // Roll specified number of six-sided dice
        System.out.println("Player rolled " + numberOfDice + " dice:");
        for (int i = 0; i < diceResults.size(); i++) {
            System.out.println("Dice " + (i + 1) + ": " + diceResults.get(i));
        }
        return diceResults;
    }
}
