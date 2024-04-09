package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Character.Character;
import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private final List<Character> characters;
    private final List<Enemy> enemies;

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
     * @param damage The amount of damage to apply to each enemy.
     */
    public void applyDamageToAllEnemies(int damage) {
        for (Enemy enemy : enemies) {
            enemy.takeDamage(damage);
            System.out.println("Applied " + damage + " damage to " + enemy.getName() + ". Health is now " + enemy.getHealth() + ".");

        }
//        enemies.removeAll(defeatedEnemies); // Remove all defeated enemies from the list
    }


}
