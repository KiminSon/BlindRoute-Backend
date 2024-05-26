package com.lake.blindroute.domain.tmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Coordinates {
    @JsonProperty("searchPoiInfo")
    private SearchPoiInfo searchPoiInfo;

    @Data
    public static class SearchPoiInfo {
        @JsonProperty("pois")
        private Pois pois;
    }

    @Data
    public static class Pois {
        @JsonProperty("poi")
        private List<CoordinateDetail> poi;
    }

    @Data
    public static class CoordinateDetail {
        @JsonProperty("frontLat")
        private String frontLat;

        @JsonProperty("frontLon")
        private String frontLon;
    }
}
