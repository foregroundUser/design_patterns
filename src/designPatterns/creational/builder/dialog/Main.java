package designPatterns.creational.builder.dialog;

public class Main {
    public static void main(String[] args) {
        Dialog warningDialog = new Dialog.Builder()
                .setHeight(200)
                .setWidth(300)
                .setTitle("Warning u are closing")
                .setBody("If u are close this dialog u cant return that data !")
                .build();
        System.out.println(warningDialog);
    }
}
