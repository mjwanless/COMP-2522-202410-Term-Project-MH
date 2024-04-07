package ca.bcit.comp2522.termproject.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Enemy  {
    private String name;
    private int strength;
    private int health;
    private int defense;
    private int evade;
    private Texture image; // LibGDX class to handle images

    public Enemy(String name, int strength, int health, int defense, int evade, String imagePath) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.evade = evade;
        this.image = new Texture(Gdx.files.internal(imagePath));
    }

    // Getters and possibly setters for each property
    public String getName() { return name; }
    public int getStrength() { return strength; }
    public int getHealth() { return health; }
    public int getDefense() { return defense; }
    public int getEvade() { return evade; }
    public Texture getImage() {
        return image;
    }
    public String getHealthString() {
        return "HP: " + this.health + "/" + this.health; // if you don't have max health
        // if you had a maxHealth property it would be "HP: " + this.health + "/" + this.maxHealth;
    }
    public abstract String getImagePath();
    public abstract String getStatsAsString();

    // You should also have a dispose method to dispose of the Texture when done
    public void dispose() {
        image.dispose();
    }
}
