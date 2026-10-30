package designPatterns.creational.abstractFactory;

public class ClassicFactory implements FurnitureFactory {
    @Override
    public Sofa createSofa() {
        return new ClassicSofa();
    }

    @Override
    public Table createTable() {
        return new ClassicTable();
    }
}
