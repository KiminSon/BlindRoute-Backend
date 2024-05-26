package com.lake.blindroute.domain.tmap;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lake.blindroute.ApiKeyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmapService {

    private final ApiKeyConfig apiKeyConfig;

    public BusRoute searchRoute(Locations locations) throws IOException, InterruptedException {
        Coordinates startCoordinates = getCoordinates(locations.getStartPoint());
        Coordinates endCoordinates = getCoordinates(locations.getEndPoint());

        return null;
    }

    public Coordinates getCoordinates(String location) throws IOException, InterruptedException {
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://apis.openapi.sk.com/tmap/pois?version=1&searchKeyword=" + encodedLocation))
                .header("Accept", "text/plain")
                .header("appKey", apiKeyConfig.getApiKey())
                .GET()
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(response.body(), Coordinates.class);
    }
}
