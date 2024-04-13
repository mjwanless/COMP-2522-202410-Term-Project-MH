package ca.bcit.comp2522.termproject.Enemy;
/**
 * Represents a Vulture enemy within the game. Vultures are known for their balance between
 * strength, health, and defense, with a notable ability to evade attacks, making them
 * agile opponents in combat. This class extends the {@link Enemy} abstract class, providing
 * specific implementations for Vultures, including their attributes and an image path for
 * their visual representation in the game environment.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Vulture extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Vulture.jpg";
    private static final int DEFAULT_STRENGTH = 3;
    private static final int DEFAULT_HEALTH = 10;
    private static final int DEFAULT_DEFENSE = 3;
    private static final int DEFAULT_EVADE = 3;
    // Default constructor
    /**
     * Constructs a default Vulture enemy with pre-defined attributes. Initializes
     * the Vulture with a specific set of stats and an image path, making it
     * distinguishable among other enemy types.
     */
    public Vulture() {
        this("Vulture", DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Constructs a Vulture enemy with customizable attributes, allowing for the
     * creation of Vultures with specified stats. This constructor enhances the
     * flexibility in enemy configuration and representation within the game.
     *
     * @param name      The name of the Vulture enemy.
     * @param strength  The strength attribute of the Vulture.
     * @param health    The health attribute of the Vulture.
     * @param defense   The defense attribute of the Vulture.
     * @param evade     The evade chance of the Vulture.
     * @param imagePath The file path to the Vulture's image.
     */
    public Vulture(final String name, final int strength, final int health,
                   final int defense, final int evade, final String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Vulture enemy with a specified name while using default stats,
     * providing an alternative for naming Vulture enemies uniquely while keeping
     * their attribute values consistent.
     *
     * @param name The name of the Vulture enemy.
     */
    public Vulture(final String name) {
        this(name, DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Vulture. This method
     * fulfills the abstract method requirement from the {@link Enemy} class,
     * ensuring the Vulture has a distinct visual identity within the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Vulture's core statistics, including
     * its name, strength, health, defense, and evade values. This method offers a
     * quick textual representation of the Vulture's primary attributes for gameplay
     * and debugging purposes.
     *
     * @return A formatted string summarizing the Vulture's stats.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Vulture, detailing its name and all key
     * attributes. This method enhances readability and simplifies logging or displaying
     * enemy information within the game.
     *
     * @return A string representation of the Vulture enemy.
     */
    @Override
    public String toString() {
        return "Vulture{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}
