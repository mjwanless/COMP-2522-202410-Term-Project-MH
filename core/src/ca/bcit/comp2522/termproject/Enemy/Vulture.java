package ca.bcit.comp2522.termproject.Enemy;

public class Vulture extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Vulture.jpg";

    // Default constructor
    public Vulture() {
        this("Vulture", 3, 10, 3, 3, IMAGE_PATH);
    }

    public Vulture(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Vulture(String name) {
        super(name, 3, 10, 3, 3, IMAGE_PATH);
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
        return "Vulture{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}
