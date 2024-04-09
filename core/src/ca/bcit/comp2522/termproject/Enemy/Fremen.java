package ca.bcit.comp2522.termproject.Enemy;
/**
 * Represents the Fremen, a desert-dwelling enemy character in the game. This class extends
 * the {@link Enemy} abstract class, providing specific implementations for the Fremen's
 * attributes and behaviors. The Fremen are known for their resilience, agility, and
 * combat skills, reflected in their stats.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024

 */
public class Fremen extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Fremen.jpg";

    // Default constructor
    /**
     * Constructs a Fremen enemy with default attributes. This constructor initializes
     * the Fremen with predefined stats and an image path, setting it apart from other
     * enemy types.
     */
    public Fremen() {
        this("Fremen", 4, 12, 1, 3, IMAGE_PATH);
    }
    /**
     * Constructs a Fremen enemy with customizable attributes. Allows for the creation
     * of Fremen enemies with specific stats, enhancing flexibility in enemy configuration.
     *
     * @param name      The name of the Fremen enemy.
     * @param strength  The strength attribute of the Fremen.
     * @param health    The health attribute of the Fremen.
     * @param defense   The defense attribute of the Fremen.
     * @param evade     The evade chance of the Fremen.
     * @param imagePath The file path to the Fremen's image.
     */
    public Fremen(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Fremen enemy with a specified name while using default stats.
     * This constructor provides an alternative naming option for Fremen enemies
     * while maintaining consistent attribute values.
     *
     * @param name The name of the Fremen enemy.
     */
    public Fremen(String name) {
        super(name, 4, 12, 1, 3, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Fremen. This method
     * fulfills the abstract method requirement from the {@link Enemy} class, providing
     * a way to visually identify the Fremen within the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Fremen's core statistics, including
     * its name, strength, health, defense, and evade values. This method offers a
     * succinct way to represent the Fremen's primary attributes textually.
     *
     * @return A formatted string representing the Fremen's statistics.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Fremen, detailing its name and key attributes.
     * This method provides a readable summary of the Fremen's specifications, aiding in quick
     * reference and comparison of enemy traits.
     *
     * @return A string representation of the Fremen enemy.
     */
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

