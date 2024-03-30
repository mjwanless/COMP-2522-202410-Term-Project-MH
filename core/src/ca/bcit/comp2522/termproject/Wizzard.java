package ca.bcit.comp2522.termproject;

public class Wizzard extends Character{
    private int strength;
    private int defense;
    private int evade;

    public Wizzard(String name, int strength, int defense, int evade) {
        super(name);
        this.strength = strength;
        this.defense = defense;
        this.evade = evade;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getEvade() {
        return evade;
    }

    @Override
    public String toString() {
        return "Wizzard{" +
                "name='" + getName() + '\'' +
                ", strength=" + strength +
                ", defense=" + defense +
                ", evade=" + evade +
                '}';
    }
}
