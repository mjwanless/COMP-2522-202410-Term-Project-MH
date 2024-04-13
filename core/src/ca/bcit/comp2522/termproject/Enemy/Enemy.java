package ca.bcit.comp2522.termproject.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/**
 * Represents an abstract Enemy in the game.
 *
 * @author Malcom Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 *
 */
public abstract class Enemy  {
    private final String name;
    private final int strength;
    private int health;
    private final int defense;
    private final int evade;
    private final Texture image; // LibGDX class to handle images
    /**
     * Constructs an Enemy object.
     *
     * @param name      The name of the enemy.
     * @param strength  The strength of the enemy.
     * @param health    The initial health of the enemy.
     * @param defense   The defense of the enemy.
     * @param evade     The evasion rate of the enemy.
     * @param imagePath The file path to the image representing the enemy.
     */
    public Enemy(final String name, final int strength, final int health, final int defense, final int evade,
                 final String imagePath) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.evade = evade;
        this.image = new Texture(Gdx.files.internal(imagePath));
    }

    // Getters and possibly setters for each property
    /**
     * Gets the name of the enemy.
     *
     * @return The name of the enemy.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the strength of the enemy.
     *
     * @return The strength of the enemy.
     */
    public int getStrength() {
        return strength;
    }
    /**
     * Gets the health of the enemy.
     *
     * @return The health of the enemy.
     */
    public int getHealth() {
        return health;
    }
    /**
     * Gets the defense of the enemy.
     *
     * @return The defense of the enemy.
     */
    public int getDefense() {
        return defense;
    }
    /**
     * Gets the evasion rate of the enemy.
     *
     * @return The evasion rate of the enemy.
     */
    public int getEvade() {
        return evade;
    }
    /**
     * Gets the image representing the enemy.
     *
     * @return The image representing the enemy.
     */
    public Texture getImage() {
        return image;
    }

    /**
     * Gets the file path to the image representing the enemy.
     *
     * @return The file path to the image representing the enemy.
     */
    public abstract String getImagePath();
    /**
     * Gets a string representation of the enemy's stats.
     *
     * @return A string representing the enemy's stats.
     */
    public abstract String getStatsAsString();

    // You should also have a dispose method to dispose of the Texture when done
    /**
     * Disposes of the enemy's image.
     */
    public void dispose() {
        image.dispose();
    }
    /**
     * Reduces the enemy's health by the specified amount.
     *
     * @param damage The amount of damage to be dealt to the enemy.
     */
    public void takeDamage(final int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            // Enemy is defeated
            // Implement removal from the battle or other actions
            System.out.println("Character is dead!");
        }
    }
}
