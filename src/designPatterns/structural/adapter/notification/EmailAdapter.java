package designPatterns.structural.adapter.notification;

public class EmailAdapter implements NotificationService {
    private final EmailClient client;
    private final String subject;

    public EmailAdapter(EmailClient client, String subject) {
        this.client = client;
        this.subject = subject;
    }

    @Override
    public void send(String recipient, String message) {
        client.sendEmail(recipient, subject, message);
    }
}
