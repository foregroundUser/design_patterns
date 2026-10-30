package designPatterns.creational.factory;

public abstract class Logistics {

    protected abstract Transport createTransport();

    public void planDelivery(String cargo, String destination) {
        Transport transport = createTransport();
        transport.deliver(cargo, destination);
    }
}
