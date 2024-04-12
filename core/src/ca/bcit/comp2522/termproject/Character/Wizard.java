package ca.bcit.comp2522.termproject.Character;
/**
 * The Wizard class encapsulates a character archetype known for its magical prowess,
 * specializing in casting spells for offense, defense, or utility. Wizards typically
 * have a balance of strength and defense but excel in situations where magic can
 * turn the tide of battle.
 * <p>
 * This class extends the abstract {@link Character} class, defining the Wizard's
 * unique attributes and functionalities. It includes specifics such as the Wizard's
 * strength, health, defense, and evade statistics, along with a distinct image path
 * for visual representation.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */
public class Wizard extends Character {
    private static final String IMAGE_PATH = "Characters/Wizard.jpg";
    /**
     * Constructs a Wizard character with predefined attributes. The initialization
     * includes setting the Wizard's stats to reflect its magical capabilities and
     * its role within the game.
     */
    public Wizard() {
        // Assuming you have specific values for Wizard's stats
        // and you are storing the image in the 'Characters' directory.
        super("Wizard", 5, 20, 5, 1, IMAGE_PATH);
    }
    // Since strength, health, defense, and evade are already in the Character class,
    // you don't need to redeclare them here unless you have class-specific behavior.
    /**
     * Returns the file path to the image representing the Wizard. The path is
     * relative to the project's resources directory, facilitating the retrieval
     * and display of the Wizard's visual representation.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Wizard's core statistics,
     * including its name, strength, health, defense, and evade values. This method
     * enables a succinct textual representation of the Wizard's primary attributes,
     * useful for summaries or in-game stat displays.
     *
     * @return A formatted string representing the Wizard's statistics.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    // Override toString() to include Wizard-specific properties if there are any.
    /**
     * Returns a string representation of the Wizard, detailing its name and all
     * key attributes inherited from the {@link Character} class. This method
     * provides an easily readable summary of the Wizard's specifications, aiding
     * in quick reference and comparison of character traits.
     *
     * @return A string representation of the Wizard character.
     */
    @Override
    public String toString() {
        return "Wizard{"
                + "name='" + getName() + '\''
                + ", strength=" + getStrength()
                + ", health=" + getHealth()
                + ", defense=" + getDefense()
                + ", evade=" + getEvade()
                + '}';
    }
}
