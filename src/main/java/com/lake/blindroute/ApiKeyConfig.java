package com.lake.blindroute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Slf4j
@Configuration
@RequiredArgsConstructor
@PropertySource("classpath:api.properties")
public class ApiKeyConfig {

    private final Environment environment;

    public String getApiKey(){
        return environment.getProperty("key");
    }
}
