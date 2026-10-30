package designPatterns.creational.factory;

public class Ship implements Transport {
    @Override
    public void deliver(String cargo, String destination) {
        System.out.println("Ship: '" + cargo + "' yuki " + destination + " ga dengiz orqali yetkazilmoqda");
    }
}
