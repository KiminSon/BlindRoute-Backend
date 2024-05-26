package com.lake.blindroute.domain.tmap;

import com.lake.blindroute.ApiKeyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TmapService {

    private final ApiKeyConfig apiKeyConfig;

    public BusRoute searchRoute(Locations locations){
        return null;
    }
}
