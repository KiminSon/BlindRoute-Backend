package com.lake.blindroute.domain.tmap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TmapRoute {
    @JsonProperty("metaData")
    private MetaData metaData;

    @Data
    public static class MetaData {
        @JsonProperty("requestParameters")
        private RequestParameters requestParameters;

        @JsonProperty("plan")
        private Plan plan;
    }

    @Data
    public static class RequestParameters{
        @JsonProperty("busCount")
        private int busCount;
    }

    @Data
    public static class Plan{
        @JsonProperty("itineraries")
        private List<Itineraries> itineraries;
    }

    @Data
    public static class Itineraries{

    }
}
