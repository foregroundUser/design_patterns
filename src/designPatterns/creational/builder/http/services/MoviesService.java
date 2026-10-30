package designPatterns.creational.builder.http.services;

import designPatterns.creational.builder.http.models.response.MovieResponse;
import designPatterns.creational.builder.http.models.response.MovieDetail;

import java.io.IOException;
import java.util.List;

public interface MoviesService {
    List<MovieResponse> latest() throws IOException;

    List<MovieResponse> search(String query) throws IOException;

    MovieDetail detail(String contentUrl) throws IOException;
}
