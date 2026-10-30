package designPatterns.creational.factory;

public class Main {
    public static void main(String[] args) {
        Logistics road = new RoadLogistics();
        Logistics sea = new SeaLogistics();
        Logistics air = new AirLogistics();

        road.planDelivery("Mebel", "Samarqand");
        sea.planDelivery("Konteyner", "Rotterdam");
        air.planDelivery("Dori-darmon", "Dubay");
    }
}
