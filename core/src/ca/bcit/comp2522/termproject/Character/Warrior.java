package ca.bcit.comp2522.termproject.Character;

public class Warrior extends Character {
    private static final String IMAGE_PATH = "Characters/Warrior.jpg";

    public Warrior() {
        // Assuming you have specific values for Paladin's stats
        // and you are storing the image in the 'Characters' directory.
        super("Warrior", 5, 20, 5, 1, IMAGE_PATH);
    }

    // Since strength, health, defense, and evade are already in the Character class,
    // you don't need to redeclare them here unless you have class-specific behavior.
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    // Override toString() to include Warrior-specific properties if there are any.
    @Override
    public String toString() {
        return "Warrior{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}
