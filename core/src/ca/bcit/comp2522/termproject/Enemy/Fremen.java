package ca.bcit.comp2522.termproject.Enemy;

public class Fremen extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Fremen.jpg";

    // Default constructor
    public Fremen() {
        this("Fremen", 4, 12, 1, 3, IMAGE_PATH);
    }

    public Fremen(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Fremen(String name) {
        super(name, 4, 12, 1, 3, IMAGE_PATH);
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
        return "Fremen{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}

