package designPatterns.creational.builder.http.services;

import designPatterns.creational.builder.http.models.response.LoginResponse;

import java.io.IOException;

public interface AuthService {
    void register(String email, String username, String password) throws IOException;

    LoginResponse verify(String email, String otp) throws IOException;

    LoginResponse login(String identifier, String password) throws IOException;
}
