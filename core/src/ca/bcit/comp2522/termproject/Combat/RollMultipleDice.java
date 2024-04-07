package ca.bcit.comp2522.termproject.Combat;

import java.util.ArrayList;
import java.util.List;

public class RollMultipleDice {

    private final GenerateDie generateDie;

    public RollMultipleDice() {
        generateDie = new GenerateDie();
    }

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
