package ca.bcit.comp2522.termproject.Combat;

import ca.bcit.comp2522.termproject.Locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Generates enemies for different locations in the game world.
 * Enemies are selected randomly from predefined pools based on the specified location.
 *
 * @author Malcolm Wanless
 * @author Heraldo Abreu
 *
 * @version 2024
 *
 */
public final class EnemyGeneration {
    /** The map of enemy pools for each location. */
    private static final Map<Locations, List<Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy>>> ENEMY_POOLS
            = new HashMap<>();

    private static final Logger LOGGER = Logger.getLogger(EnemyGeneration.class.getName());

    // Private constructor to prevent instantiation
    private EnemyGeneration() {
        // Empty constructor
    }
    static {
        // Initialize the enemy pools for each location
        java.util.List<Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy>> forestEnemies
                = new java.util.ArrayList<>();
        forestEnemies.add(ca.bcit.comp2522.termproject.Enemy.Dog.class);
        forestEnemies.add(ca.bcit.comp2522.termproject.Enemy.Vandit.class);
        ENEMY_POOLS.put(ca.bcit.comp2522.termproject.Locations.FOREST, forestEnemies);

        // Add more locations and their corresponding enemies as needed
        java.util.List<Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy>> desertEnemies
                = new java.util.ArrayList<>();
        desertEnemies.add(ca.bcit.comp2522.termproject.Enemy.Fremen.class);
        desertEnemies.add(ca.bcit.comp2522.termproject.Enemy.Vulture.class);
        ENEMY_POOLS.put(ca.bcit.comp2522.termproject.Locations.DESERT, desertEnemies);

        java.util.List<Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy>> volcanoEnemies
                = new java.util.ArrayList<>();
        volcanoEnemies.add(ca.bcit.comp2522.termproject.Enemy.Slug.class);
        volcanoEnemies.add(ca.bcit.comp2522.termproject.Enemy.Lizard.class);
        ENEMY_POOLS.put(ca.bcit.comp2522.termproject.Locations.VOLCANO, volcanoEnemies);

        java.util.List<Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy>> castleEnemies
                = new java.util.ArrayList<>();
        castleEnemies.add(ca.bcit.comp2522.termproject.Enemy.Sentinel.class);
        castleEnemies.add(ca.bcit.comp2522.termproject.Enemy.Wolf.class);
        ENEMY_POOLS.put(ca.bcit.comp2522.termproject.Locations.CASTLE, castleEnemies);
    }
    /**
     * Generates a list of enemies for the specified location.
     *
     * @param location         the location where enemies will be generated.
     * @param numberOfEnemies  the number of enemies to generate.
     * @return                 a list of Enemy instances generated for the specified location.
     */
    public static List<ca.bcit.comp2522.termproject.Enemy.Enemy> generateEnemiesForLocation(
            final Locations location,
            final int numberOfEnemies) {
        List<ca.bcit.comp2522.termproject.Enemy.Enemy> enemies = new ArrayList<>();
        List<Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy>> availableEnemies = ENEMY_POOLS.get(location);

        Random random = new Random();
        for (int i = 0; i < numberOfEnemies; i++) {
            try {
                Class<? extends ca.bcit.comp2522.termproject.Enemy.Enemy> enemyClass
                        = availableEnemies.get(random.nextInt(availableEnemies.size()));
                ca.bcit.comp2522.termproject.Enemy.Enemy enemy = enemyClass.getDeclaredConstructor().newInstance();
                enemies.add(enemy);
            } catch (InstantiationException | IllegalAccessException
                     | java.lang.reflect.InvocationTargetException | NoSuchMethodException e) {
                LOGGER.log(Level.SEVERE, "Error generating enemy", e);
            }
        }

        return enemies;
    }

}
