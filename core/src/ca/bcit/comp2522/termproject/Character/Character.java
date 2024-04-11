package ca.bcit.comp2522.termproject.Character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
/**
 * Represents a character in a game, providing basic attributes such as name,
 * strength, health, defense, and evade. It also manages the character's image
 * representation using the LibGDX framework. This class is abstract, meaning it
 * is intended to be subclassed with specific character implementations.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 *
 */

public abstract class Character {
    private String name;
    private int strength;
    private int health;
    private int defense;
    private int evade;
    private Texture image; // LibGDX class to handle images
    /**
     * Constructs a new Character instance with specified attributes and an image path.
     *
     * @param name The name of the character.
     * @param strength The strength attribute of the character.
     * @param health The health attribute of the character.
     * @param defense The defense attribute of the character.
     * @param evade The evade chance of the character.
     * @param imagePath The file path to the character's image.
     */
    public Character(String name, int strength, int health, int defense, int evade, String imagePath) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.evade = evade;
        this.image = new Texture(Gdx.files.internal(imagePath));
    }

    // Getters and possibly setters for each property
    /**
     * Returns the name of the character.
     *
     * @return The character's name.
     */
    public String getName() { return name; }
    /**
     * Returns the strength of the character.
     *
     * @return The character's strength.
     */
    public int getStrength() { return strength; }
    /**
     * Returns the health of the character.
     *
     * @return The character's health.
     */
    public int getHealth() { return health; }
    /**
     * Returns the defense attribute of the character.
     *
     * @return The character's defense.
     */
    public int getDefense() { return defense; }
    /**
     * Returns the evade chance of the character.
     *
     * @return The character's evade chance.
     */
    public int getEvade() { return evade; }


    public void heal(int amount) {
        health += amount;
        if (health > 20) { // Assuming there's a maxHealth field that tracks the character's maximum health.
            health = 20;
        }
    }

    /**
     * Method to apply damage to the character.
     *
     * @param damage The amount of damage to apply.
     */
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            // Enemy is defeated
            // Implement removal from the battle or other actions
            System.out.println("Character is dead!");
        }
    }

    /**
     * Returns the character's image.
     *
     * @return The Texture instance representing the character's image.
     */
    public Texture getImage() {
        return image;
    }
    /**
     * Returns a string representation of the character's health.
     *
     * @return The health status as a string.
     */
    public String getHealthString() {
        return "HP: " + this.health + "/" + this.health; // if you don't have max health
        // if you had a maxHealth property it would be "HP: " + this.health + "/" + this.maxHealth;
    }
    /**
     * Abstract method to be implemented by subclasses to return the image path of the character.
     *
     * @return The image path as a string.
     */
    public abstract String getImagePath();
    /**
     * Abstract method to be implemented by subclasses to return the character's statistics as a string.
     *
     * @return The character's stats as a string.
     */
    public abstract String getStatsAsString();

    // You should also have a dispose method to dispose of the Texture when done
    /**
     * Disposes of the Texture resource when it is no longer needed, to prevent memory leaks.
     */
    public void dispose() {
        image.dispose();
    }
}
