package ca.bcit.comp2522.termproject.Combat;

import java.util.Random;

public class GenerateDie {

    private final Random random;

    public GenerateDie() {
        random = new Random();
    }

    public int rollDie(int numberOfFaces) {
        if (numberOfFaces <= 0) {
            throw new IllegalArgumentException("Number of faces must be greater than zero");
        }
        return random.nextInt(numberOfFaces) + 1;
    }
}
