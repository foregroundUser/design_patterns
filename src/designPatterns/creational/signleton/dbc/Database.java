package designPatterns.creational.signleton.dbc;


public class Database {
    private static Database instance;

    private Database() {
        System.out.println("Database connected !");
    }

    public static synchronized Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public String pull() {
        return "Pulled";
    }

}
