package designPatterns.creational.factory;

public class AirLogistics extends Logistics {
    @Override
    protected Transport createTransport() {
        return new Plane();
    }
}
