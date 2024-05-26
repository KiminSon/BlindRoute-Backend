package com.lake.blindroute.domain.tmap;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TmapController {
    private final TmapService tmapService;

    @PostMapping("/route")
    public BusRoute searchRoute(@RequestBody Locations locations) {
        return tmapService.searchRoute(locations);
    }
}
