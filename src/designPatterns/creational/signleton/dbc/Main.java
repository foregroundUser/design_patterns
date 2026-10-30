package designPatterns.creational.signleton.dbc;

class Main {
    static void main() {
        Database database = Database.getInstance();

        System.out.println(database.pull());
    }
}