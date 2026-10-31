package designPatterns.structural.adapter.notification;

public class TelegramClient {
    public void sendTelegramMessage(String chatId, String text) {
        System.out.println("[Telegram] chatId=" + chatId + ": " + text);
    }
}
