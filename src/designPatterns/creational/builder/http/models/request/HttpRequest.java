package designPatterns.creational.builder.http.models.request;

import designPatterns.creational.builder.http.utils.HttpMethod;

public class HttpRequest {
    private final HttpMethod method;
    private final String url;
    private final String header;
    private final String body;

    private HttpRequest(Builder b) {
        this.method = b.method;
        this.url = b.url;
        this.header = b.header;
        this.body = b.body;
    }

    public HttpMethod getMethod() { return method; }
    public String getUrl()        { return url; }
    public String getHeader()     { return header; }
    public String getBody()       { return body; }

    @Override
    public String toString() {
        return method + " " + url + "\nHeader: " + header + "\nBody: " + body + "\n";
    }

    public static class Builder {
        private HttpMethod method = HttpMethod.GET;
        private String url;
        private String header;
        private String body;

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        public Builder header(String header) {
            this.header = header;
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            if (url == null) {
                throw new IllegalStateException("url majburiy");
            }
            return new HttpRequest(this);
        }
    }
}
