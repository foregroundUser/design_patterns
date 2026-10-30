package designPatterns.creational.abstractFactory;

public class ClassicSofa implements Sofa {
    @Override
    public void sitOn() {
        System.out.println("Klassik divan: o'ymakor, baxmal qoplamali");
    }
}
