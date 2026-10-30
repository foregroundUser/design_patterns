package designPatterns.creational.builder.http.client;

import designPatterns.creational.builder.http.models.request.HttpRequest;
import designPatterns.creational.builder.http.models.response.HttpResponse;
import designPatterns.creational.builder.http.utils.HttpMethod;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpClient {


    private final OkHttpClient client = new OkHttpClient();
    private static final int MAX_ATTEMPTS=3;
    private static final long BASE_DELAY_MS=500;
    public HttpResponse send(HttpRequest request) throws IOException {
        IOException lastError = null;
        RequestBody body = RequestBody.create(request.getBody(), MediaType.parse("application/json"));

        Request.Builder builder = new Request.Builder()
                .url(request.getUrl())
                .method(request.getMethod().name(), body);

        if (request.getHeader() != null) {
            String[] h = request.getHeader().split(":", 2);
            builder.header(h[0].trim(), h[1].trim());
        }

        try (Response response = client.newCall(builder.build()).execute()) {
            Map<String, String> headers = new LinkedHashMap<>();
            response.headers().names().forEach(name -> headers.put(name, response.header(name)));
            return new HttpResponse(response.code(), headers, response.body().string());
        }
    }

    }
