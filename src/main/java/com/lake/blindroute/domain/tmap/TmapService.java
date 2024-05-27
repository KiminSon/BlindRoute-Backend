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

        TmapRoute tmapRoute = responseOnlyBus(startCoordinates, endCoordinates);

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

    public TmapRoute responseOnlyBus(Coordinates startCoordinates, Coordinates endCoordinates) throws IOException, InterruptedException {
        String startX = startCoordinates.getSearchPoiInfo().getPois().getPoi().get(0).getFrontLon();
        String startY = startCoordinates.getSearchPoiInfo().getPois().getPoi().get(0).getFrontLat();
        String endX = endCoordinates.getSearchPoiInfo().getPois().getPoi().get(0).getFrontLon();
        String endY = endCoordinates.getSearchPoiInfo().getPois().getPoi().get(0).getFrontLat();

        String requestBody = String.format("{\"startX\":\"%s\",\"startY\":\"%s\",\"endX\":\"%s\",\"endY\":\"%s\"}", startX, startY, endX, endY);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://apis.openapi.sk.com/transit/routes"))
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .header("appKey", apiKeyConfig.getApiKey())
                .method("POST", HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return null;
    }
}
