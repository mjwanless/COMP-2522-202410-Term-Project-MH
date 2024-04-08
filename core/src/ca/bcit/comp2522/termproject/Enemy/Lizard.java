package ca.bcit.comp2522.termproject.Enemy;

public class Lizard extends Enemy {
    private static final String IMAGE_PATH = "Enemies/LizardSoldier.jpg";

    // Default constructor
    public Lizard() {
        this("Lizard", 4, 10, 4, 1, IMAGE_PATH);
    }

    public Lizard(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Lizard(String name) {
        super(name, 4, 10, 4, 1, IMAGE_PATH);
    }

    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }


    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }

    @Override
    public String toString() {
        return "Lizard{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}

