package com.monitoring.energy.integration;

import com.monitoring.energy.integration.model.CounterLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CounterApi extends RestClient {

    @Value("${integration.api.counter-location}")
    private String counterLocationApi;

    public CounterApi(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public CounterLocation getCounterLocation(Long id) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(counterLocationApi)
                .queryParam("id", id);
        return get(getHttpHeadersWithMediaTypeJson(), CounterLocation.class, uriBuilder.toUriString()).getBody();
    }
}
