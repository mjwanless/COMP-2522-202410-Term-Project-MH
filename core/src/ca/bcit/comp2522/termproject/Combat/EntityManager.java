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

    public void applyDamageToEnemy(Enemy enemy, int damage) {
        if (enemy != null) {
            enemy.takeDamage(damage);
            if (enemy.getHealth() <= 0) {
                enemies.remove(enemy);
                // Additional logic for when an enemy is defeated
            }
        }
    }


}
