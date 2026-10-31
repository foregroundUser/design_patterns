package designPatterns.structural.adapter.notification;

public class EmailClient {
    public void sendEmail(String emailAddress, String subject, String body) {
        System.out.println("[Email] to " + emailAddress + " | subject: " + subject + " | body: " + body);
    }
}
