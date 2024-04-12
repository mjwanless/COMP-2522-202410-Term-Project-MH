package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages entities involved in combat, such as characters and enemies.
 * The EntityManager class provides methods to apply damage to characters and enemies
 * during combat rounds.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 *
 */
public class EntityManager {
    /** The list of characters involved in combat. */
    public final List<Character> characters;

    /** The list of enemies involved in combat. */
    public final List<Enemy> enemies;

    /**
     * Constructs an EntityManager with the specified arrays of characters and enemies.
     *
     * @param charactersArray an array of Character instances representing combat characters.
     * @param enemiesArray an array of Enemy instances representing combat enemies.
     */
    public EntityManager(final Character[] charactersArray, final Enemy[] enemiesArray) {
        this.characters = new ArrayList<>();
        this.enemies = new ArrayList<>();

        // Convert arrays to lists for easier management
        this.characters.addAll(Arrays.asList(charactersArray));
        this.enemies.addAll(Arrays.asList(enemiesArray));
    }

    /**
     * Applies damage to all enemies.
     * Removes any enemies whose health drops to zero or below.
     *
     * @param damage The amount of damage to apply to each enemy.
     */
    public void applyDamageToAllEnemies(final int damage) {
        List<Enemy> defeatedEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            enemy.takeDamage(damage);
            System.out.println("Applied " + damage + " damage to " + enemy.getName() + ". Health is now "
                    + enemy.getHealth() + ".");
            if (enemy.getHealth() <= 0) {
                defeatedEnemies.add(enemy);
            }
        }
        enemies.removeAll(defeatedEnemies); // Remove all defeated enemies from the list
    }

    /**
     * Applies damage to all characters.
     * Removes any characters whose health drops to zero or below.
     *
     * @param damage The amount of damage to apply to each character.
     */
    public void applyDamageToAllCharacters(final int damage) {
        List<Character> defeatedCharacters = new ArrayList<>();
        for (Character character : characters) {
            character.takeDamage(damage);
            System.out.println("Applied " + damage + " damage to " + character.getName() + ". Health is now "
                    + character.getHealth() + ".");
            if (character.getHealth() <= 0) {
                defeatedCharacters.add(character);
            }
        }
        characters.removeAll(defeatedCharacters); // Remove all defeated characters from the list
    }
}
