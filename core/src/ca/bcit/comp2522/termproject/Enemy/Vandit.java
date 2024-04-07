package ca.bcit.comp2522.termproject.Enemy;

public class Vandit extends Enemy{
    private static final String IMAGE_PATH = "Enemies/Vandit.jpg";

    // Default constructor
    public Vandit() {
        this("Vandit", 2, 10, 2, 1, IMAGE_PATH);
    }

    public Vandit(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Vandit(String name) {
        super(name,2, 10, 2, 1, IMAGE_PATH);
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
        return "Vandit{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}
