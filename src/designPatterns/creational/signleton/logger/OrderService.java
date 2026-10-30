package designPatterns.creational.signleton.logger;

public class OrderService {
    public void createOrder(String product) {
        Logger.getInstance().log("OrderService: '" + product + "' buyurtma qilindi");
    }

}
