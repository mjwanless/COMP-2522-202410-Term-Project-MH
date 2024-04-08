package ca.bcit.comp2522.termproject.Enemy;

public class Sentinel extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Sentinel.jpg";

    // Default constructor
    public Sentinel() {
        this("Sentinel", 4, 15, 4, 0, IMAGE_PATH);
    }

    public Sentinel(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Sentinel(String name) {
        super(name, 4, 15, 4, 0, IMAGE_PATH);
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
        return "Sentinel{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}

