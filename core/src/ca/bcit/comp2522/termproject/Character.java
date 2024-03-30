package ca.bcit.comp2522.termproject;

abstract class Character {
    private String name;

    public Character(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
