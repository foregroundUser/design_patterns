package designPatterns.creational.factory;

public class Truck implements Transport {
    @Override
    public void deliver(String cargo, String destination) {
        System.out.println("Truck: '" + cargo + "' yuki " + destination + " ga yo'l orqali yetkazilmoqda");
    }
}
