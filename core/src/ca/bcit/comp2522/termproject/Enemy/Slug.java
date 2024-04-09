package ca.bcit.comp2522.termproject.Enemy;
/**
 * Represents a Slug enemy in the game, characterized by its unique stats and visual representation.
 * Slugs are designed to pose a balanced challenge to players, with average strength, health, defense,
 * and a slight chance to evade attacks. This class extends the {@link Enemy} abstract class, providing
 * a specific implementation for Slugs, including their attributes and an image path for their visual
 * representation within the game environment.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Slug extends Enemy {
    private static final String IMAGE_PATH = "Enemies/Lavaslug.jpg";

    // Default constructor
    /**
     * Constructs a default Slug enemy with pre-defined attributes. Initializes the Slug
     * with a specific set of stats and an image path, making it distinguishable among
     * other enemy types.
     */
    public Slug() {
        this("Slug", 4, 10, 4, 1, IMAGE_PATH);
    }
    /**
     * Constructs a Slug enemy with customizable attributes, allowing for the creation
     * of Slug enemies with specified stats. This constructor enhances the flexibility
     * in enemy configuration and representation within the game.
     *
     * @param name      The name of the Slug enemy.
     * @param strength  The strength attribute of the Slug.
     * @param health    The health attribute of the Slug.
     * @param defense   The defense attribute of the Slug.
     * @param evade     The evade chance of the Slug.
     * @param imagePath The file path to the Slug's image.
     */
    public Slug(String name, int strength, int health, int defense, int evade, String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Slug enemy with a specified name while using default stats. This
     * constructor provides an alternative for naming Slug enemies uniquely while keeping
     * their attribute values consistent.
     *
     * @param name The name of the Slug enemy.
     */
    public Slug(String name) {
        super(name, 4, 10, 4, 1, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Slug. This method fulfills
     * the abstract method requirement from the {@link Enemy} class, ensuring the Slug
     * has a distinct visual identity within the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Slug's core statistics, including
     * its name, strength, health, defense, and evade values. This method offers a
     * quick textual representation of the Slug's primary attributes for gameplay
     * and debugging purposes.
     *
     * @return A formatted string summarizing the Slug's stats.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Slug, detailing its name and all key
     * attributes. This method enhances readability and simplifies logging or
     * displaying enemy information within the game.
     *
     * @return A string representation of the Slug enemy.
     */
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
