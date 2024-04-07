package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Enemy.Enemy;
import ca.bcit.comp2522.termproject.Enemy.Dog;
import ca.bcit.comp2522.termproject.Enemy.Vandit;
import ca.bcit.comp2522.termproject.Locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EnemyGeneration {

    private static final Map<Locations, List<Class<? extends Enemy>>> enemyPools = new HashMap<>();

    static {
        // Initialize the enemy pools for each location
        List<Class<? extends Enemy>> forestEnemies = new ArrayList<>();
        forestEnemies.add(Dog.class);
        forestEnemies.add(Vandit.class);
        enemyPools.put(Locations.FOREST, forestEnemies);

        // Add more locations and their corresponding enemies as needed
        // enemyPools.put(Locations.DESERT, Arrays.asList(DesertEnemy1.class, DesertEnemy2.class));
        // enemyPools.put(Locations.VOLCANO, Arrays.asList(VolcanoEnemy1.class));
    }

    public static List<Enemy> generateEnemiesForLocation(Locations location, int numberOfEnemies) {
        List<Enemy> enemies = new ArrayList<>();
        List<Class<? extends Enemy>> availableEnemies = enemyPools.get(location);

        Random random = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            try {
                Class<? extends Enemy> enemyClass = availableEnemies.get(random.nextInt(availableEnemies.size()));
                Enemy enemy = enemyClass.getDeclaredConstructor().newInstance();
                enemies.add(enemy);
            } catch (Exception e) {
                e.printStackTrace(); // Log or handle the exception as needed
            }
        }

        return enemies;
    }

    // Other methods...
}
