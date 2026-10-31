package designPatterns.structural.bridge.game;

abstract class Game {
    protected Developer developer;

    public Game(Developer developer) {
        this.developer = developer;
    }

    public abstract void create();

}
