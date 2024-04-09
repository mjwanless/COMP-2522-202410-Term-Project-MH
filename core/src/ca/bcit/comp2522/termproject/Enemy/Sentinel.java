package ca.bcit.comp2522.termproject.Enemy;
/**
 * Represents a Sentinel enemy within the game. Sentinels are formidable adversaries known for
 * their robust defense and health, making them challenging opponents. This class extends the
 * {@link Enemy} abstract class, providing specific implementations for the Sentinel's attributes
 * and behaviors. The Sentinel is characterized by its lack of evasion capabilities, compensated
 * by higher health and defense stats, and is visually represented by a unique image.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Sentinel extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Sentinel.jpg";

    // Default constructor
    /**
     * Constructs a default Sentinel enemy with pre-defined attributes. This constructor initializes
     * the Sentinel with a specific set of stats and an image path, distinguishing it from other
     * enemy types.
     */
    public Sentinel() {
        this("Sentinel", 4, 15, 4, 0, IMAGE_PATH);
    }
    /**
     * Constructs a Sentinel enemy with customizable attributes, allowing for the creation of
     * Sentinels with specific stats. This constructor enhances the flexibility in how enemies
     * are presented and behave within the game.
     *
     * @param name      The name of the Sentinel enemy.
     * @param strength  The strength attribute of the Sentinel.
     * @param health    The health attribute of the Sentinel.
     * @param defense   The defense attribute of the Sentinel.
     * @param evade     The evade chance of the Sentinel, typically set to zero to reflect its
     *                  character design.
     * @param imagePath The file path to the Sentinel's image.
     */
    public Sentinel(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Sentinel enemy with a specified name while using default stats, providing an
     * alternative for creating Sentinels with unique names but uniform attribute values.
     *
     * @param name The name of the Sentinel enemy.
     */
    public Sentinel(String name) {
        super(name, 4, 15, 4, 0, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Sentinel. This method satisfies the
     * abstract method requirement from the {@link Enemy} class, ensuring the Sentinel has a
     * unique visual identity within the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Sentinel's core statistics, including its name,
     * strength, health, defense, and evade values. This method allows for a quick textual
     * representation of the Sentinel's primary attributes.
     *
     * @return A formatted string summarizing the Sentinel's stats.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Provides a string representation of the Sentinel, detailing its name and all key attributes.
     * This method enhances readability and simplifies logging or displaying enemy information.
     *
     * @return A string representation of the Sentinel enemy.
     */
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

