package ca.bcit.comp2522.termproject.Enemy;

/**
 * Represents a Dog enemy in the game. This class extends the {@link Enemy} abstract class,
 * providing a specific implementation for the Dog enemy, including its attributes and behaviors.
 * The Dog is characterized by its specific strength, health, defense, and evade stats, along with
 * a unique image to represent it within the game environment.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Dog extends Enemy {
    private static final String IMAGE_PATH = "Enemies/GuardDog.jpg";
    private static final int DEFAULT_STRENGTH = 3;
    private static final int DEFAULT_HEALTH = 13;
    private static final int DEFAULT_DEFENSE = 3;
    private static final int DEFAULT_EVADE = 2;

    // Default constructor
    /**
     * Constructs a Dog enemy with default attributes. This constructor initializes
     * the Dog with predefined stats and an image path.
     */
    public Dog() {
        this("Dog", DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Constructs a Dog enemy with customizable attributes. This allows for the creation
     * of Dog enemies with specific stats, providing flexibility in enemy configuration.
     *
     * @param name      The name of the Dog enemy.
     * @param strength  The strength attribute of the Dog.
     * @param health    The health attribute of the Dog.
     * @param defense   The defense attribute of the Dog.
     * @param evade     The evade chance of the Dog.
     * @param imagePath The file path to the Dog's image.
     */
    public Dog(final String name, final int strength, final int health,
               final int defense, final int evade, final String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Dog enemy with a specified name while using default stats.
     * This constructor provides a way to name Dog enemies differently while
     * maintaining the same attribute values.
     *
     * @param name The name of the Dog enemy.
     */
    public Dog(final String name) {
        this(name, DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Dog. This method
     * fulfills the abstract method requirement from the {@link Enemy} class.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }

    /**
     * Provides a formatted string containing the Dog's core statistics,
     * including its name, strength, health, defense, and evade values. This method
     * enables a succinct textual representation of the Dog's primary attributes.
     *
     * @return A formatted string representing the Dog's statistics.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Dog, detailing its name and all
     * key attributes inherited from the {@link Enemy} class. This method provides
     * an easily readable summary of the Dog's specifications.
     *
     * @return A string representation of the Dog enemy.
     */
    @Override
    public String toString() {
        return "Dog{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}
