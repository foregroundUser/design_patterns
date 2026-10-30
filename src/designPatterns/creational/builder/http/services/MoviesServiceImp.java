package designPatterns.creational.builder.http.services;

import com.google.gson.Gson;
import designPatterns.creational.builder.http.client.HttpClient;
import designPatterns.creational.builder.http.models.request.HttpRequest;
import designPatterns.creational.builder.http.models.response.HttpResponse;
import designPatterns.creational.builder.http.models.response.MovieResponse;
import designPatterns.creational.builder.http.models.response.MovieDetail;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class MoviesServiceImp implements MoviesService {
    private static final String BASE_URL = "https://apisozo.azamov.me/api/contents";

    private final HttpClient client = new HttpClient();
    private final Gson gson = new Gson();

    record ItemsResponse(List<MovieResponse> items) {
    }

    @Override
    public List<MovieResponse> latest() throws IOException {
        return get("/latest", ItemsResponse.class).items();
    }

    @Override
    public List<MovieResponse> search(String query) throws IOException {
        return get("/search?q=" + encode(query), ItemsResponse.class).items();
    }

    @Override
    public MovieDetail detail(String contentUrl) throws IOException {
        return get("/detail?url=" + encode(contentUrl), MovieDetail.class);
    }

    // GET yuboradi, JSON javobni kerakli turga o'giradi
    private <T> T get(String path, Class<T> type) throws IOException {
        HttpRequest request = new HttpRequest.Builder()
                .url(BASE_URL + path)
                .build();

        HttpResponse response = client.send(request);
        if (response.status() != 200) {
            throw new IOException("Server xatosi: " + response.status());
        }
        return gson.fromJson(response.body(), type);
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
