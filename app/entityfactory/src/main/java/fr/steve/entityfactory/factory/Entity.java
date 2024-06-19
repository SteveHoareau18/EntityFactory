package fr.steve.entityfactory.factory;

public abstract class Entity {

    private static int nextId = 0;
    private final int id;

    public Entity() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }
}
