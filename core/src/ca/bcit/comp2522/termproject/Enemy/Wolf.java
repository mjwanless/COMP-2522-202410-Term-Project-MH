package ca.bcit.comp2522.termproject.Enemy;

public class Wolf extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Wolf.jpg";

    // Default constructor
    public Wolf() {
        this("Wolf", 3, 8, 2, 2, IMAGE_PATH);
    }

    public Wolf(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Wolf(String name) {
        super(name, 3, 8, 2, 2, IMAGE_PATH);
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
        return "Wolf{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}

