package designPatterns.creational.signleton.logger;

public class Main {
    public static void main(String[] args) {
        new UserService().register("Aziz");
        new OrderService().createOrder("Iphone 17 orniax");
        new DeliveryService().delivery("Iphone 17 orniax");
        new UserService().register("Dilnoza");

        Logger a = Logger.getInstance();
        Logger b = Logger.getInstance();
        System.out.println("a == b ? " + (a == b));
        System.out.println("Jami loglar: " + a.count());
    }
}
