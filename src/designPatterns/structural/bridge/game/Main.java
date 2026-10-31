package designPatterns.structural.bridge.game;

public class Main {
    public static void main(String[] args) {
        Developer javaDeveloper = new JavaDeveloper();
        Developer rubyDeveloper = new RubyDeveloper();
        Developer goDeveloper = new GoDeveloper();

        Game g1 = new RacingGame(javaDeveloper);
        Game g2 = new FootballGame(rubyDeveloper);
        Game g3 = new RacingGame(goDeveloper);

        g1.create();
        g2.create();
        g3.create();
    }


}
