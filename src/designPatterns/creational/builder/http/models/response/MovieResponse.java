package designPatterns.creational.builder.http.models.response;

public record MovieResponse(String title, Integer year, int rating, String category, String contentUrl) {
}
