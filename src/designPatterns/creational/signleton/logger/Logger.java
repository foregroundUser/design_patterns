package designPatterns.creational.signleton.logger;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

  class  Logger {

    private static Logger instance;

    private final List<String> logs = new ArrayList<>();

    private Logger() {
        System.out.println("Logger yaratildi (faqat bir marta)");
    }

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        String line = "[" + LocalTime.now().withNano(0) + "] " + message;
        logs.add(line);
        System.out.println(line);
    }

    public int count() {
        return logs.size();
    }
}
