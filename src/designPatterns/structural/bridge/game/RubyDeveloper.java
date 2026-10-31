package designPatterns.structural.bridge.game;

public class RubyDeveloper implements  Developer {
    @Override
    public void writeCode() {
        System.out.println("Ruby Developer writes ruby codes");
    }
}
