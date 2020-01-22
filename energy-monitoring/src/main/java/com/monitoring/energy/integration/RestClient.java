package com.monitoring.energy.integration;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestClient {
    private final RestTemplate restTemplate;

    public RestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }


    protected <R> ResponseEntity<R> get(HttpHeaders headers, Class<R> responseType, String uri, Object... pathVariables) {
        return this.restTemplate.exchange(uri, HttpMethod.GET, toHttpEntity(headers, null), responseType, pathVariables);
    }


    protected static HttpHeaders getHttpHeadersWithMediaTypeJson() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    private static <T> HttpEntity<T> toHttpEntity(HttpHeaders headers, T body) {
        return new HttpEntity(body, headers);
    }

}
