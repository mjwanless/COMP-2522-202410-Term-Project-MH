package ca.bcit.comp2522.termproject.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/**
 * Represents an abstract base class for enemy entities in the game. This class
 * defines common attributes and behaviors that all enemies share, such as name,
 * strength, health, defense, and evade. Additionally, it handles the graphical
 * representation of enemies using the LibGDX Texture class.
 * <p>
 * Subclasses must implement the abstract methods to provide specific functionality
 * for different types of enemies, including their image paths and statistical information.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 */

public abstract class Enemy  {
    private final String name;
    private final int strength;
    private final int health;
    private final int defense;
    private final int evade;
    private final Texture image; // LibGDX class to handle images
    /**
     * Constructs an Enemy instance with specified attributes and an image path.
     * Initializes the enemy's properties and loads its image using the provided
     * path and the LibGDX framework.
     *
     * @param name      The name of the enemy.
     * @param strength  The strength attribute of the enemy.
     * @param health    The health attribute of the enemy.
     * @param defense   The defense attribute of the enemy.
     * @param evade     The evade chance of the enemy.
     * @param imagePath The file path to the enemy's image.
     */
    public Enemy(String name, int strength, int health, int defense, int evade, String imagePath) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.evade = evade;
        this.image = new Texture(Gdx.files.internal(imagePath));
    }

    // Getters and possibly setters for each property
    /**
     * Returns the name of the enemy.
     *
     * @return The enemy's name.
     */
    public String getName() { return name; }
    /**
     * Returns the strength of the enemy.
     *
     * @return The enemy's strength.
     */
    public int getStrength() { return strength; }
    /**
     * Returns the health of the enemy.
     *
     * @return The enemy's health.
     */
    public int getHealth() { return health; }
    /**
     * Returns the defense attribute of the enemy.
     *
     * @return The enemy's defense.
     */
    public int getDefense() { return defense; }
    /**
     * Returns the evade chance of the enemy.
     *
     * @return The enemy's evade chance.
     */
    public int getEvade() { return evade; }
    /**
     * Returns the image representing the enemy.
     *
     * @return The enemy's image as a Texture.
     */
    public Texture getImage() {
        return image;
    }
    /**
     * Returns the enemy's current health in a string format, showing both current
     * and maximum health values. Ideal for displaying health status in the UI.
     *
     * @return A string representing the enemy's health.
     */
    public String getHealthString() {
        return "HP: " + this.health + "/" + this.health; // if you don't have max health
        // if you had a maxHealth property it would be "HP: " + this.health + "/" + this.maxHealth;
    }
    /**
     * Abstract method that subclasses must implement to return the image path
     * of the enemy.
     *
     * @return The image path as a string.
     */
    public abstract String getImagePath();
    /**
     * Abstract method that subclasses must implement to provide a string
     * representation of the enemy's stats.
     *
     * @return A string detailing the enemy's statistics.
     */
    public abstract String getStatsAsString();
    // You should also have a dispose method to dispose of the Texture when done
    /**
     * Disposes of the Texture resource associated with the enemy to free up memory.
     * Should be called when the enemy is no longer in use.
     */
    public void dispose() {
        image.dispose();
    }
}
