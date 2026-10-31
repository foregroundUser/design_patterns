package designPatterns.structural.bridge.game;

public class GoDeveloper implements  Developer{
    @Override
    public void writeCode() {
        System.out.println("Go Developer writes codes ");
    }
}
