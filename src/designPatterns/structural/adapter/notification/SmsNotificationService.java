package designPatterns.structural.adapter.notification;

public class SmsNotificationService implements NotificationService {
    @Override
    public void send(String recipient, String message) {
        System.out.println("[SMS] to " + recipient + ": " + message);
    }
}
