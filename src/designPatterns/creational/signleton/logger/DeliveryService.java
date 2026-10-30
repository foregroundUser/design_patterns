package designPatterns.creational.signleton.logger;

public class DeliveryService {
    public  void delivery(String product){
        Logger.getInstance().log(product+" Delivered successfully !");
    }
}
