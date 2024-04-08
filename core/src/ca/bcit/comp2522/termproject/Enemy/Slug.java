package ca.bcit.comp2522.termproject.Enemy;

public class Slug extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Lavaslug.jpg";

    // Default constructor
    public Slug() {
        this("Slug", 4, 10, 4, 1, IMAGE_PATH);
    }

    public Slug(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Slug(String name) {
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
        return "Slug{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}
