package designPatterns.creational.abstractFactory;

public class Main {
    public static void main(String[] args) {
        furnishRoom(new ModernFactory());
        System.out.println();
        furnishRoom(new ClassicFactory());
    }

    static void furnishRoom(FurnitureFactory factory) {
        Sofa sofa = factory.createSofa();
        Table table = factory.createTable();
        sofa.sitOn();
        table.putThings();
    }
}
