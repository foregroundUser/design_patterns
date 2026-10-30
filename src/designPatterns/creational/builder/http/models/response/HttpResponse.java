package designPatterns.creational.builder.http.models.response;

import java.util.Map;

public record HttpResponse(int status, Map<String, String> headers, String body) {
    @Override
    public String toString() {
        return "Status: " + status + "\nHeaders: " + headers + "\nBody: " + body;
    }
}
