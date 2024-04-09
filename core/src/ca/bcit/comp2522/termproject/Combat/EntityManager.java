package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    public final List<Character> characters;
    public final List<Enemy> enemies;

    public EntityManager(Character[] charactersArray, Enemy[] enemiesArray) {
        this.characters = new ArrayList<>();
        this.enemies = new ArrayList<>();

        // Convert arrays to lists for easier management
        for (Character character : charactersArray) {
            this.characters.add(character);
        }
        for (Enemy enemy : enemiesArray) {
            this.enemies.add(enemy);
        }
    }

    /**
     * Applies damage to all enemies.
     * Removes any enemies whose health drops to zero or below.
     *
     * @param damage The amount of damage to apply to each enemy.
     */
    public void applyDamageToAllEnemies(int damage) {
        List<Enemy> defeatedEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            enemy.takeDamage(damage);
            System.out.println("Applied " + damage + " damage to " + enemy.getName() + ". Health is now " + enemy.getHealth() + ".");
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
        public void applyDamageToAllCharacters(int damage) {
            List<Character> defeatedCharacters = new ArrayList<>();
            for (Character character : characters) {
                character.takeDamage(damage);
                System.out.println("Applied " + damage + " damage to " + character.getName() + ". Health is now " + character.getHealth() + ".");
                if (character.getHealth() <= 0) {
                    defeatedCharacters.add(character);
                }
            }
            characters.removeAll(defeatedCharacters); // Remove all defeated characters from the list
        }


}
