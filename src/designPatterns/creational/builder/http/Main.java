package designPatterns.creational.builder.http;

import designPatterns.creational.builder.http.models.response.LoginResponse;
import designPatterns.creational.builder.http.models.response.MovieResponse;
import designPatterns.creational.builder.http.models.response.MovieDetail;
import designPatterns.creational.builder.http.services.AuthService;
import designPatterns.creational.builder.http.services.AuthServiceImp;
import designPatterns.creational.builder.http.services.MoviesService;
import designPatterns.creational.builder.http.services.MoviesServiceImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final AuthService auth = new AuthServiceImp();
    private static final MoviesService movies = new MoviesServiceImp();
    private static final Scanner in = new Scanner(System.in);

    private static String username;
    private static List<MovieResponse> shown = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n" + (username == null ? "[kirmagan]" : "[" + username + "]"));
            System.out.println("1) Ro'yxatdan o'tish  2) Kirish  3) Yangi kinolar  4) Qidirish  5) Detail  0) Chiqish");
            String choice = ask("> ");

            try {
                switch (choice) {
                    case "1" -> register();
                    case "2" -> login();
                    case "3" -> showList(movies.latest());
                    case "4" -> showList(movies.search(ask("Nom: ")));
                    case "5" -> showDetail(ask("Ro'yxatdagi raqam: "));
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("Noto'g'ri tanlov");
                }
            } catch (IOException e) {
                System.out.println("Xato: " + e.getMessage());
            }
        }
    }

    private static void register() throws IOException {
        String email = ask("Email: ");
        auth.register(email, ask("Username: "), ask("Parol: "));
        System.out.println("Tasdiqlash kodi emailga yuborildi.");

        LoginResponse result = auth.verify(email, ask("Kod: "));
        username = result.user().username();
        System.out.println("Ro'yxatdan o'tdingiz, " + username + "!");
    }

    private static void login() throws IOException {
        LoginResponse result = auth.login(ask("Email yoki username: "), ask("Parol: "));
        username = result.user().username();
        System.out.println("Xush kelibsiz, " + username + "!");
    }

    private static void showList(List<MovieResponse> list) {
        shown = list;
        if (list.isEmpty()) {
            System.out.println("Hech narsa topilmadi");
        }
        for (int i = 0; i < list.size(); i++) {
            MovieResponse m = list.get(i);
            System.out.println((i + 1) + ". " + m.title() + " (" + m.year() + ")");
        }
    }

    private static void showDetail(String number) throws IOException {
        int index;
        try {
            index = Integer.parseInt(number) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Raqam kiriting");
            return;
        }
        if (index < 0 || index >= shown.size()) {
            System.out.println("Bunday raqam yo'q (avval 3 yoki 4 ni tanlang)");
            return;
        }

        MovieDetail d = movies.detail(shown.get(index).contentUrl());
        System.out.println("\n" + d.title() + " (" + d.year() + ")");
        System.out.println("Davomiyligi: " + d.duration());
        System.out.println("Davlat:      " + d.country());
        System.out.println("Rejissyor:   " + d.director());
        System.out.println("Janr:        " + d.genres());
        System.out.println("\n" + d.description());
    }

    private static String ask(String text) {
        System.out.print(text);
        return in.nextLine().trim();
    }
}
