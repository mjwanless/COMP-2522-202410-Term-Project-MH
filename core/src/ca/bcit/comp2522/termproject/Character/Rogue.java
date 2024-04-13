package ca.bcit.comp2522.termproject.Character;
/**
 * The Rogue class represents a character archetype known for its agility, stealth, and
 * evasion capabilities. Rogues are typically characterized by their ability to deal damage
 * while avoiding direct confrontation through high evade values.
 * <p>
 * This class extends the abstract {@link Character} class, specifying the Rogue's unique
 * attributes and behaviors. The Rogue is defined with specific values for strength, health,
 * defense, and evade, and it uses a unique image to visually represent the character.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 * @version 2024
 *
 */
public class Rogue extends Character {
    private static final String IMAGE_PATH = "Characters/Rogue.jpg";
    private static final String ROGUE_NAME = "Rogue";
    private static final int ROGUE_STRENGTH = 2;
    private static final int ROGUE_HEALTH = 20;
    private static final int ROGUE_DEFENSE = 4;
    private static final int ROGUE_EVADE = 4;
    /**
     * Constructs a Rogue character with predefined attributes. The Rogue's
     * attributes are initialized to represent its specialized role focusing on
     * agility and evasion.
     */
    public Rogue() {
        // Assuming you have specific values for Rouge's stats
        // and you are storing the image in the 'Characters' directory.
        super(ROGUE_NAME, ROGUE_STRENGTH, ROGUE_HEALTH, ROGUE_DEFENSE, ROGUE_EVADE, IMAGE_PATH);
    }

    // Since strength, health, defense, and evade are already in the Character class,
    // you don't need to redeclare them here unless you have class-specific behavior.
    /**
     * Returns the file path to the image representing the Rogue.
     * This path is relative to the project's resources directory, allowing
     * for easy access and integration of the character's visual representation.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Rogue's core statistics,
     * including its name, strength, health, defense, and evade values. This method
     * allows for a quick textual representation of the Rogue's capabilities, useful
     * for debugging or displaying character information within the game.
     *
     * @return A formatted string representing the Rogue's statistics.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    // Override toString() to include Rouge-specific properties if there are any.
    /**
     * Returns a string representation of the Rogue, detailing its name and all
     * attributes inherited from the {@link Character} class. This method enhances
     * readability and usability by providing a concise summary of the Rogue's
     * properties.
     *
     * @return A string representation of the Rogue character.
     */
    @Override
    public String toString() {
        return "Rogue{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}
