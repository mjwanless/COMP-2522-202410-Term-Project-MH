package ca.bcit.comp2522.termproject.Enemy;

/**
 * Represents a Lizard enemy in the game. The Lizard class extends the {@link Enemy} class,
 * providing specific attributes for a Lizard Soldier type enemy. It includes custom stats
 * suitable for a mid-level adversary and utilizes a specific image for visual representation
 * in the game environment.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Lizard extends Enemy {
    private static final int DEFAULT_STRENGTH = 4;
    private static final int DEFAULT_HEALTH = 10;
    private static final int DEFAULT_DEFENSE = 4;
    private static final int DEFAULT_EVADE = 1;
    private static final String IMAGE_PATH = "Enemies/LizardSoldier.jpg";
    // Default constructor
    /**
     * Constructs a default Lizard enemy with pre-defined attributes. This constructor
     * initializes the Lizard with a set of base stats and an image path.
     */
    public Lizard() {
        this("Lizard", DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Constructs a Lizard enemy with customizable attributes. This allows for the creation
     * of Lizard enemies with specified stats, providing flexibility in how enemies are
     * presented and behave in the game.
     *
     * @param name      The name of the Lizard enemy.
     * @param strength  The strength attribute of the Lizard.
     * @param health    The health attribute of the Lizard.
     * @param defense   The defense attribute of the Lizard.
     * @param evade     The evade chance of the Lizard.
     * @param imagePath The file path to the Lizard's image.
     */
    public Lizard(final String name, final int strength, final int health,
                  final int defense, final int evade, final String imagePath) {
        super(name, strength, health, defense, evade, imagePath);
    }
    /**
     * Constructs a Lizard enemy with a specified name while using default stats.
     * This constructor offers an alternative for creating Lizard enemies with unique
     * names but uniform attribute values.
     *
     * @param name The name of the Lizard enemy.
     */
    public Lizard(final String name) {
        this(name, DEFAULT_STRENGTH, DEFAULT_HEALTH, DEFAULT_DEFENSE, DEFAULT_EVADE, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Lizard. This method
     * satisfies the abstract method from the {@link Enemy} class, ensuring the Lizard
     * has a unique visual identity in the game.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Lizard's core statistics, such as
     * its name, strength, health, defense, and evade values. This method allows for
     * a quick textual representation of the Lizard's attributes.
     *
     * @return A formatted string summarizing the Lizard's stats.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Provides a string representation of the Lizard, including its name and all key attributes.
     * This method enhances readability and simplifies logging or displaying enemy information.
     *
     * @return A string representation of the Lizard enemy.
     */
    @Override
    public String toString() {
        return "Lizard{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}

