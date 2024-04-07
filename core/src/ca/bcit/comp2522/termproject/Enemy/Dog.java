package ca.bcit.comp2522.termproject.Enemy;

public class Dog extends Enemy {
    private static final String IMAGE_PATH = "Enemies/GuardDog.jpg";

    // Default constructor
    public Dog() {
        this("Dog", 3, 13, 3, 2, IMAGE_PATH);
    }

    public Dog(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }

    public Dog(String name) {
        super(name, 3, 13, 3, 2, IMAGE_PATH);
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
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}
