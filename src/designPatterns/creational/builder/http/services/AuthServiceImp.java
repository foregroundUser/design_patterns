package designPatterns.creational.builder.http.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import designPatterns.creational.builder.http.client.HttpClient;
import designPatterns.creational.builder.http.models.request.HttpRequest;
import designPatterns.creational.builder.http.models.response.HttpResponse;
import designPatterns.creational.builder.http.models.response.LoginResponse;
import designPatterns.creational.builder.http.utils.HttpMethod;

import java.io.IOException;
import java.util.Map;

public class AuthServiceImp implements AuthService {
    private static final String BASE_URL = "https://apisozo.azamov.me/api/auth";

    private final HttpClient client = new HttpClient();
    private final Gson gson = new Gson();

    @Override
    public void register(String email, String username, String password) throws IOException {
        post("/register", Map.of("email", email, "username", username, "password", password));
    }

    @Override
    public LoginResponse verify(String email, String otp) throws IOException {
        HttpResponse response = post("/register/verify", Map.of("email", email, "otp", otp));
        return gson.fromJson(response.body(), LoginResponse.class);
    }

    @Override
    public LoginResponse login(String identifier, String password) throws IOException {
        HttpResponse response = post("/login", Map.of("identifier", identifier, "password", password));
        return gson.fromJson(response.body(), LoginResponse.class);
    }

    private HttpResponse post(String path, Map<String, String> data) throws IOException {
        HttpRequest request = new HttpRequest.Builder()
                .url(BASE_URL + path)
                .method(HttpMethod.POST)
                .header("Content-Type: application/json")
                .body(gson.toJson(data))
                .build();

        HttpResponse response = client.send(request);
        if (response.status() < 200 || response.status() >= 300) {
            throw new IOException(errorMessage(response));
        }
        return response;
    }

    private String errorMessage(HttpResponse response) {
        try {
            JsonObject json = gson.fromJson(response.body(), JsonObject.class);
            return json.get("message").getAsString();
        } catch (Exception e) {
            return "Server xatosi: " + response.status();
        }
    }
}
