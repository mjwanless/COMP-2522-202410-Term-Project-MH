package ca.bcit.comp2522.termproject.Enemy;
/**
 * Represents a Vandit enemy in the game. Vandits are characterized by their lower
 * strength and defense but have a slight chance to evade attacks, making them tricky
 * opponents despite their apparent weakness. This class extends the {@link Enemy}
 * abstract class, providing a specific implementation for Vandits, including their
 * attributes and an image path for their visual representation within the game
 * environment.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Vandit extends Enemy{
    private static final String IMAGE_PATH = "Enemies/Vandit.jpg";

    // Default constructor
    /**
     * Constructs a default Vandit enemy with pre-defined attributes. Initializes
     * the Vandit with a specific set of stats and an image path, making it
     * distinguishable among other enemy types.
     */
    public Vandit() {
        this("Vandit", 2, 10, 2, 1, IMAGE_PATH);
    }
    /**
     * Constructs a Vandit enemy with customizable attributes, allowing for the
     * creation of Vandit enemies with specified stats. This constructor enhances
     * flexibility in enemy configuration and representation within the game.
     *
     * @param name      The name of the Vandit enemy.
     * @param strength  The strength attribute of the Vandit.
     * @param health    The health attribute of the Vandit.
     * @param defense   The defense attribute of the Vandit.
     * @param evade     The evade chance of the Vandit.
     * @param imagePath The file path to the Vandit's image.
     */
    public Vandit(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Vandit enemy with a specified name while using default stats,
     * providing an alternative for naming Vandit enemies uniquely while keeping
     * their attribute values consistent.
     *
     * @param name The name of the Vandit enemy.
     */
    public Vandit(String name) {
        super(name,2, 10, 2, 1, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Vandit. This method
     * fulfills the abstract method requirement from the {@link Enemy} class,
     * ensuring the Vandit has a distinct visual identity within the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Vandit's core statistics, including
     * its name, strength, health, defense, and evade values. This method offers a
     * quick textual representation of the Vandit's primary attributes for gameplay
     * and debugging purposes.
     *
     * @return A formatted string summarizing the Vandit's stats.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Vandit, detailing its name and all key
     * attributes. This method enhances readability and simplifies logging or displaying
     * enemy information within the game.
     *
     * @return A string representation of the Vandit enemy.
     */
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
