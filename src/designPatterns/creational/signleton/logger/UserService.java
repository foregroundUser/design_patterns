package designPatterns.creational.signleton.logger;

public class UserService {
    public void register(String name) {
        Logger.getInstance().log("UserService: " + name + " ro'yxatdan o'tdi");
    }
}
