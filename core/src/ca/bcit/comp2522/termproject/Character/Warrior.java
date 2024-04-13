package ca.bcit.comp2522.termproject.Character;
/**
 * The Warrior class represents a character archetype with a focus on high strength and
 * defense capabilities. Warriors are known for their ability to withstand significant
 * damage and deliver powerful attacks, making them a robust frontline presence in combat.
 * <p>
 * This class extends the abstract {@link Character} class, providing a concrete implementation
 * that defines the specific attributes and behaviors of a Warrior, including its image path
 * and a textual representation of its statistics.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 * @version 2024
 *
 */
public class Warrior extends Character {
    private static final String IMAGE_PATH = "Characters/Warrior.jpg";
    private static final String WARRIOR_NAME = "Warrior";
    private static final int WARRIOR_STRENGTH = 5;
    private static final int WARRIOR_HEALTH = 20;
    private static final int WARRIOR_DEFENSE = 5;
    private static final int WARRIOR_EVADE = 1;
    /**
     * Constructs a Warrior character with predefined attributes. The Warrior's attributes
     * are initialized to highlight its combat strengths, particularly in terms of offense
     * and defense.
     */
    public Warrior() {
        // Assuming you have specific values for Paladin's stats
        // and you are storing the image in the 'Characters' directory.
        super(WARRIOR_NAME, WARRIOR_STRENGTH, WARRIOR_HEALTH, WARRIOR_DEFENSE, WARRIOR_EVADE, IMAGE_PATH);
    }
    // Since strength, health, defense, and evade are already in the Character class,
    // you don't need to redeclare them here unless you have class-specific behavior.
    /**
     * Returns the file path to the image representing the Warrior.
     * This path is relative to the project's resources directory, facilitating
     * the retrieval and display of the Warrior's visual representation.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Warrior's core statistics,
     * including its name, strength, health, defense, and evade values. This method
     * enables a succinct textual representation of the Warrior's primary attributes,
     * useful for character summaries or in-game stat displays.
     *
     * @return A formatted string representing the Warrior's statistics.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    // Override toString() to include Warrior-specific properties if there are any.
    /**
     * Returns a string representation of the Warrior, detailing its name and all
     * key attributes inherited from the {@link Character} class. This method provides
     * an easily readable summary of the Warrior's specifications, aiding in quick
     * reference and comparison of character traits.
     *
     * @return A string representation of the Warrior character.
     */
    @Override
    public String toString() {
        return "Warrior{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}
