package designPatterns.creational.builder.http.client;

import designPatterns.creational.builder.http.models.request.HttpRequest;
import designPatterns.creational.builder.http.models.response.HttpResponse;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

// HTTP/1.1 ni kutubxonasiz, to'g'ridan-to'g'ri TCP socket orqali yuboradi
public class TcpHttpClient {
    private static final int TIMEOUT_MS = 10_000;

    public HttpResponse send(HttpRequest request) throws IOException {
        URI uri = URI.create(request.getUrl());
        boolean https = "https".equalsIgnoreCase(uri.getScheme());
        String host = uri.getHost();
        int port = uri.getPort() != -1 ? uri.getPort() : (https ? 443 : 80);

        String path = uri.getRawPath() == null || uri.getRawPath().isEmpty() ? "/" : uri.getRawPath();
        if (uri.getRawQuery() != null) {
            path += "?" + uri.getRawQuery();
        }

        try (Socket socket = open(host, port, https)) {
            writeRequest(socket.getOutputStream(), request, host, port, https, path);
            return readResponse(new BufferedInputStream(socket.getInputStream()));
        }
    }

    // 1-qadam: TCP ulanish, https bo'lsa ustidan TLS
    private Socket open(String host, int port, boolean https) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), TIMEOUT_MS);
        socket.setSoTimeout(TIMEOUT_MS);
        if (!https) {
            return socket;
        }

        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket ssl = (SSLSocket) factory.createSocket(socket, host, port, true);
        SSLParameters params = ssl.getSSLParameters();
        params.setEndpointIdentificationAlgorithm("HTTPS"); // sertifikat shu domenga tegishliligini tekshiradi
        ssl.setSSLParameters(params);
        ssl.startHandshake();
        return ssl;
    }

    // 2-qadam: so'rovni matn ko'rinishida yozish
    //   POST /api/auth/login HTTP/1.1\r\n
    //   Host: apisozo.azamov.me\r\n
    //   ...\r\n
    //   \r\n
    //   {"identifier": ...}
    private void writeRequest(OutputStream raw, HttpRequest request, String host, int port,
                              boolean https, String path) throws IOException {
        byte[] body = request.getBody() == null ? null : request.getBody().getBytes(StandardCharsets.UTF_8);
        boolean defaultPort = port == (https ? 443 : 80);

        StringBuilder head = new StringBuilder();
        head.append(request.getMethod().name()).append(' ').append(path).append(" HTTP/1.1\r\n");
        head.append("Host: ").append(host).append(defaultPort ? "" : ":" + port).append("\r\n");
        head.append("Connection: close\r\n");          // javobdan keyin server ulanishni yopadi
        head.append("Accept-Encoding: identity\r\n");  // gzip so'ramaymiz
        if (request.getHeader() != null) {
            head.append(request.getHeader()).append("\r\n");
        }
        if (body != null) {
            head.append("Content-Length: ").append(body.length).append("\r\n");
        }
        head.append("\r\n");

        OutputStream out = new BufferedOutputStream(raw);
        out.write(head.toString().getBytes(StandardCharsets.UTF_8));
        if (body != null) {
            out.write(body);
        }
        out.flush();
    }

    // 3-qadam: status qatori, headerlar, bo'sh qator, keyin body
    private HttpResponse readResponse(InputStream in) throws IOException {
        String statusLine = readLine(in); // HTTP/1.1 200 OK
        String[] parts = statusLine.split(" ", 3);
        if (parts.length < 2 || !parts[0].startsWith("HTTP/")) {
            throw new IOException("Noto'g'ri javob: " + statusLine);
        }
        int status = Integer.parseInt(parts[1]);

        Map<String, String> headers = new LinkedHashMap<>();
        String line;
        while (!(line = readLine(in)).isEmpty()) {
            int colon = line.indexOf(':');
            if (colon > 0) {
                headers.put(line.substring(0, colon).trim(), line.substring(colon + 1).trim());
            }
        }

        byte[] body;
        String length = header(headers, "Content-Length");
        if ("chunked".equalsIgnoreCase(header(headers, "Transfer-Encoding"))) {
            body = readChunked(in);
        } else if (length != null) {
            body = readExactly(in, Integer.parseInt(length));
        } else {
            body = in.readAllBytes(); // Connection: close bo'lgani uchun oxirigacha o'qiymiz
        }
        return new HttpResponse(status, headers, new String(body, StandardCharsets.UTF_8));
    }

    // chunked format: "<hex uzunlik>\r\n<ma'lumot>\r\n" ... "0\r\n\r\n"
    private byte[] readChunked(InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        while (true) {
            String sizeLine = readLine(in);
            int semicolon = sizeLine.indexOf(';');
            int size = Integer.parseInt((semicolon >= 0 ? sizeLine.substring(0, semicolon) : sizeLine).trim(), 16);
            if (size == 0) {
                while (!readLine(in).isEmpty()) {
                    // trailer headerlarni tashlab yuboramiz
                }
                return result.toByteArray();
            }
            result.write(readExactly(in, size));
            readLine(in); // chunk oxiridagi \r\n
        }
    }

    private byte[] readExactly(InputStream in, int n) throws IOException {
        byte[] data = in.readNBytes(n);
        if (data.length != n) {
            throw new EOFException("Javob to'liq kelmadi");
        }
        return data;
    }

    // \r\n gacha bitta qatorni o'qiydi
    private String readLine(InputStream in) throws IOException {
        ByteArrayOutputStream line = new ByteArrayOutputStream();
        int b;
        while ((b = in.read()) != -1 && b != '\n') {
            if (b != '\r') {
                line.write(b);
            }
        }
        if (b == -1 && line.size() == 0) {
            throw new EOFException("Server ulanishni yopdi");
        }
        return line.toString(StandardCharsets.UTF_8);
    }

    // header nomlari katta-kichik harfga bog'liq emas
    private String header(Map<String, String> headers, String name) {
        for (Map.Entry<String, String> e : headers.entrySet()) {
            if (e.getKey().equalsIgnoreCase(name)) {
                return e.getValue();
            }
        }
        return null;
    }
}
