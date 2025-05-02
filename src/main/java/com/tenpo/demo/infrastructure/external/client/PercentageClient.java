package com.tenpo.demo.infrastructure.external.client;

import com.tenpo.demo.domain.exceptions.ApiExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PercentageClient {

    private final RestTemplate restTemplate;
    private final String percentageServiceUrl;

    public PercentageClient(RestTemplate restTemplate,
                            @Value("${external.percentage.url}") String percentageServiceUrl) {
        this.restTemplate = restTemplate;
        this.percentageServiceUrl = percentageServiceUrl;
    }

    @Cacheable("percentage")
    public double fetchPercentageFromExternalService() {
        try {
            return restTemplate.getForObject(percentageServiceUrl, Double.class);
        } catch (Exception e) {
            throw new ApiExceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR,"Error calling external percentage service");
        }
    }
}