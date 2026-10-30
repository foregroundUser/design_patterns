package designPatterns.creational.builder.http.models.response;

import java.util.List;

public record MovieDetail(String title,
                          String description,
                          Integer year,
                          String duration,
                          String country,
                          String director,
                          List<String> genres) {
}
