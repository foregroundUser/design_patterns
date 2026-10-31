package designPatterns.structural.bridge.game;

public class FootballGame extends Game {
    public FootballGame(Developer developer) {
        super(developer);
    }

    @Override
    public void create() {
        System.out.println("Football game yaratimoqda");
        developer.writeCode();
        ;
    }
}
