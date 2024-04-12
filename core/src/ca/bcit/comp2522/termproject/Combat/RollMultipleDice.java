package ca.bcit.comp2522.termproject.Combat;

import java.util.ArrayList;
import java.util.List;
/**
 * Generates a random number between 1 and the specified number of faces.
 * This class is used to simulate rolling a die in a game.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class RollMultipleDice {

    private final GenerateDie generateDie;
    /**
     * Constructs a new RollMultipleDice instance.
     */
    public RollMultipleDice() {
        generateDie = new GenerateDie();
    }
    /**
     * Rolls multiple dice with the specified number of faces.
     * The result is a list of random numbers between 1 and the number of faces.
     *
     * @param numberOfDice the number of dice to roll.
     * @param numberOfFaces the number of faces on each die.
     * @return a list of the results of the die rolls.
     */
    public List<Integer> rollDice(int numberOfDice, int numberOfFaces) {
        if (numberOfDice <= 0 || numberOfFaces <= 0) {
            throw new IllegalArgumentException("Number of dice and number of faces must be greater than zero");
        }

        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            int rollResult = generateDie.rollDie(numberOfFaces);
            results.add(rollResult);
        }
        return results;
    }
}
