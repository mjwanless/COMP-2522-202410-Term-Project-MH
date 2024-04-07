package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EnemyGeneration {

    public static List<Enemy> generateEnemies(int numberOfEnemies, Class<? extends Enemy> enemyClass) {
        List<Enemy> enemies = new ArrayList<>();

        for (int i = 0; i < numberOfEnemies; i++) {
            try {
                Enemy enemy = enemyClass.getDeclaredConstructor(String.class).newInstance("Enemy " + (i + 1));
                enemies.add(enemy);
            } catch (Exception e) {
                System.err.println("Error creating enemy: " + e.getMessage());
            }
        }

        return enemies;
    }
}
