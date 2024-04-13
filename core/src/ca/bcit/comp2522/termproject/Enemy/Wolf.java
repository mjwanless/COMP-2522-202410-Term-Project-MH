package ca.bcit.comp2522.termproject.Enemy;
/**
 * Represents a Wolf enemy within the game, characterized by its agility and balanced stats.
 * Wolves are designed to be swift adversaries, with moderate evasion and attack capabilities,
 * making them versatile opponents in combat. This class extends the {@link Enemy} abstract class,
 * providing specific implementations for the Wolf's attributes and behaviors, along with an
 * image path for visual representation in the game environment.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Wolf extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Wolf.jpg";
    private static final int DEFAULT_STRENGTH = 3;
    private static final int DEFAULT_HEALTH = 8;
    private static final int DEFAULT_DEFENSE = 2;
    private static final int DEFAULT_EVADE = 2;
    // Default constructor
    /**
     * Constructs a default Wolf enemy with pre-defined attributes. Initializes
     * the Wolf with a balanced set of stats and an image path, making it
     * distinguishable among other enemy types.
     */
    public Wolf() {
        this("Wolf", DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Constructs a Wolf enemy with customizable attributes, allowing for the creation
     * of Wolves with specified stats. This constructor enhances the flexibility in enemy
     * configuration and representation within the game.
     *
     * @param name      The name of the Wolf enemy.
     * @param strength  The strength attribute of the Wolf.
     * @param health    The health attribute of the Wolf.
     * @param defense   The defense attribute of the Wolf.
     * @param evade     The evade chance of the Wolf.
     * @param imagePath The file path to the Wolf's image.
     */
    public Wolf(final String name, final int strength, final int health,
                final int defense, final int evade, final String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Wolf enemy with a specified name while using default stats,
     * providing an alternative for naming Wolf enemies uniquely while keeping
     * their attribute values consistent.
     *
     * @param name The name of the Wolf enemy.
     */
    public Wolf(final String name) {
        this(name, DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Wolf. This method
     * fulfills the abstract method requirement from the {@link Enemy} class,
     * ensuring the Wolf has a distinct visual identity within the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Wolf's core statistics, including
     * its name, strength, health, defense, and evade values. This method offers a
     * quick textual representation of the Wolf's primary attributes for gameplay
     * and debugging purposes.
     *
     * @return A formatted string summarizing the Wolf's stats.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Wolf, detailing its name and all key
     * attributes. This method enhances readability and simplifies logging or displaying
     * enemy information within the game.
     *
     * @return A string representation of the Wolf enemy.
     */
    @Override
    public String toString() {
        return "Wolf{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}

