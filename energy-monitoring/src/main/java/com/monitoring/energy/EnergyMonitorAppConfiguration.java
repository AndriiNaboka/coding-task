package com.monitoring.energy;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@org.springframework.context.annotation.Configuration
public class EnergyMonitorAppConfiguration {
    public EnergyMonitorAppConfiguration() {
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setFieldMatchingEnabled(true).setSkipNullEnabled(true).setPropertyCondition(Conditions.isNotNull()).setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
        return mapper;
    }

    @Bean
    public RestTemplate restTemplate(
            @Value("${rest.client.timeout-read-ms}") int readTimeout,
            RestTemplateBuilder builder) {
        return builder.setReadTimeout(Duration.ofMillis(readTimeout)).build();
    }
}
