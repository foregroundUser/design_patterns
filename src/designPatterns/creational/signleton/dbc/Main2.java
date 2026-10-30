package designPatterns.creational.signleton.dbc;


import javax.xml.crypto.Data;

public class Main2 {
    static void main() {
        Database database= Database.getInstance();
        Database database2= Database.getInstance();
        System.out.println(database.pull());
        System.out.println(database2.pull());
        System.out.println(database==database2);
    }
}
