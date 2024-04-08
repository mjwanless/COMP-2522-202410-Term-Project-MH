package ca.bcit.comp2522.termproject.Combat;
import java.util.List;

public class CombatManager {
    private static Initiative currentInitiator;
    private static RollMultipleDice rollMultipleDice;

    // Initialize the rollMultipleDice object
    public static void initializeDiceRoller() {
        rollMultipleDice = new RollMultipleDice();
    }

    // Method to simulate player's attack
    private static void playerAttack(int numberOfDice) {
        // Roll the specified number of dice during player's turn
        rollDice(numberOfDice);
        // Implement other player attack logic here
        System.out.println("Player attacks!");
    }

    // Method to simulate enemy's attack
    private static void enemyAttack() {
        // Implement enemy attack logic here
        System.out.println("Enemy attacks!");
    }

    // Method to handle combat round
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

    // Method to handle turn switching when the button is clicked
    public static void switchTurn(int numberOfDice) {
        // Toggle between player and enemy turns
        currentInitiator = (currentInitiator == Initiative.PLAYER) ? Initiative.ENEMY : Initiative.PLAYER;
        // Perform combat round for the current initiator with specified number of dice
        handleCombatRound(currentInitiator, numberOfDice);
    }

    // Method to roll multiple dice using RollMultipleDice class
    private static void rollDice(int numberOfDice) {
        List<Integer> diceResults = rollMultipleDice.rollDice(numberOfDice, 6); // Roll specified number of six-sided dice
        System.out.println("Player rolled " + numberOfDice + " dice:");
        for (int i = 0; i < diceResults.size(); i++) {
            System.out.println("Dice " + (i + 1) + ": " + diceResults.get(i));
        }
    }
}
