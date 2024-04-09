package ca.bcit.comp2522.termproject.Character;
/**
 * The Paladin class represents a specific character type within the game, characterized
 * by its unique balance of strength, health, defense, and evade attributes. Paladins are
 * known for their defensive prowess and moderate attack capabilities.
 * <p>
 * This class extends the abstract {@link Character} class, providing a concrete
 * implementation that defines the specific attributes and behaviors of a Paladin, including
 * its image path and a textual representation of its statistics.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 *
 */
public class Paladin extends Character {
    private static final String IMAGE_PATH = "Characters/Paladin.jpg";
    /**
     * Constructs a Paladin character with predefined attributes. The Paladin's
     * name, strength, health, defense, and evade values are set upon instantiation,
     * along with its associated image path.
     */
    public Paladin() {
        super("Paladin", 4, 20, 6, 2, IMAGE_PATH);
    }
    /**
     * Returns the file path to the image representing the Paladin.
     * This path is relative to the project's resources directory.
     *
     * @return The image path as a String.
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }
    /**
     * Provides a formatted string containing the Paladin's statistics, including
     * its name, strength, health, defense, and evade values. This method overrides
     * the abstract method defined in the {@link Character} class.
     *
     * @return A formatted string representing the Paladin's statistics.
     */
    @Override
    public String getStatsAsString() {
        return String.format("Name: %s, Strength: %d, Health: %d, Defense: %d, Evade: %d",
                getName(), getStrength(), getHealth(), getDefense(), getEvade());
    }
    /**
     * Returns a string representation of the Paladin, detailing its name, strength,
     * health, defense, and evade attributes. This method provides a convenient way
     * to display a Paladin's core attributes as a string.
     *
     * @return A string representation of the Paladin character.
     */
    @Override
    public String toString() {
        return "Paladin{" +
                "name='" + getName() + '\'' +
                ", strength=" + getStrength() +
                ", health=" + getHealth() +
                ", defense=" + getDefense() +
                ", evade=" + getEvade() +
                '}';
    }
}
