package designPatterns.structural.bridge.game;

public class RacingGame extends  Game{
    public RacingGame(Developer developer) {
        super(developer);
    }

    @Override
    public void create() {
        System.out.println("Poyga o`yin yaratilmoqda ");
        developer.writeCode();
    }
}
