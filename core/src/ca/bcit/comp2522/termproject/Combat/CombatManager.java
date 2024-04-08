package ca.bcit.comp2522.termproject.Combat;

public class CombatManager {

    private static Initiative currentInitiator;
    // Method to simulate player's attack
    private static void playerAttack() {
        // Implement player attack logic here
        System.out.println("Player attacks!");
    }

    // Method to simulate enemy's attack
    private static void enemyAttack() {
        // Implement enemy attack logic here
        System.out.println("Enemy attacks!");
    }

    // Method to handle combat round
    public static void handleCombatRound(Initiative initiator) {
        switch (initiator) {
            case PLAYER:
                playerAttack();
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
    public static void switchTurn() {
        // Toggle between player and enemy turns
        currentInitiator = (currentInitiator == Initiative.PLAYER) ? Initiative.ENEMY : Initiative.PLAYER;
        // Perform combat round for the current initiator
        handleCombatRound(currentInitiator);
    }

}
