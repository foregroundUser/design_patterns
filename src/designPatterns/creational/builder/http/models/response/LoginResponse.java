package designPatterns.creational.builder.http.models.response;

public record LoginResponse(User user, String accessToken, String refreshToken) {

    public record User(String username, String email) {
    }
}
