package ca.bcit.comp2522.termproject.Combat;

import java.util.Random;

/**
 * Generates a random number between 1 and the specified number of faces.
 * This class is used to simulate rolling a die in a game.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class GenerateDie {

    private final Random random;
    /**
     * Constructs a new GenerateDie instance.
     */
    public GenerateDie() {
        random = new Random();
    }
    /**
     * Rolls a die with the specified number of faces.
     * The result is a random number between 1 and the number of faces.
     *
     * @param numberOfFaces the number of faces on the die.
     * @return the result of the die roll.
     */
    public int rollDie(int numberOfFaces) {
        if (numberOfFaces <= 0) {
            throw new IllegalArgumentException("Number of faces must be greater than zero");
        }
        return random.nextInt(numberOfFaces) + 1;
    }
}
