package designPatterns.creational.factory;

public class Plane implements Transport {
    @Override
    public void deliver(String cargo, String destination) {
        System.out.println("Plane: '" + cargo + "' yuki " + destination + " ga havo orqali yetkazilmoqda");
    }
}
