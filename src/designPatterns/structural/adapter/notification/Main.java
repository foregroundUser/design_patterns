package designPatterns.structural.adapter.notification;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        NotificationManager sms = new NotificationManager(new SmsNotificationService());
        NotificationManager telegram = new NotificationManager(new TelegramAdapter(new TelegramClient()));
        NotificationManager email = new NotificationManager(new EmailAdapter(new EmailClient(), "Bildirishnoma"));

        sms.notify("+998901234567", "Buyurtmangiz qabul qilindi");
        telegram.notify("123456789", "Buyurtmangiz qabul qilindi");
        email.notify("user@example.com", "Buyurtmangiz qabul qilindi");

        System.out.println();

    }
}
