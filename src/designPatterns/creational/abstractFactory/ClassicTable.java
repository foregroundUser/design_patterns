package designPatterns.creational.abstractFactory;

public class ClassicTable implements Table {
    @Override
    public void putThings() {
        System.out.println("Klassik stol: o'ymakor yog'ochdan");
    }
}
