package designPatterns.structural.adapter.notification;

public class TelegramAdapter implements NotificationService {
    private final TelegramClient client;

    public TelegramAdapter(TelegramClient client) {
        this.client = client;
    }

    @Override
    public void send(String recipient, String message) {
        client.sendTelegramMessage(recipient, message);
    }
}
