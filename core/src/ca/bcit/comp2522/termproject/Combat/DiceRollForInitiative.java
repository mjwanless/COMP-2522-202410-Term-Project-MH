package ca.bcit.comp2522.termproject.Combat;

import java.util.Random;

public class DiceRollForInitiative {

    // Enum to represent the two sides: Player and Enemy
    public enum turnOrder {
        PLAYER,
        ENEMY
    }

    // Method to roll a dice and determine whose turn it is
    public static turnOrder rollForInitiative() {
        Random random = new Random();
        int result = random.nextInt(2);

        return (result == 0) ? turnOrder.PLAYER : turnOrder.ENEMY;
    }

    public static void main(String[] args) {
        turnOrder turn = rollForInitiative();
        System.out.println("It's " + turn.toString() + "'s turn.");
    }
}

