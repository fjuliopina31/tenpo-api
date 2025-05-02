package com.tenpo.demo.infrastructure.external.service;

import com.tenpo.demo.domain.port.IPercentageClientService;
import com.tenpo.demo.infrastructure.external.client.PercentageClient;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class PercentageService implements IPercentageClientService {

    private final PercentageClient percentageClient;
    private final CacheManager cacheManager;

    public PercentageService(PercentageClient percentageClient, CacheManager cacheManager) {
        this.percentageClient = percentageClient;
        this.cacheManager = cacheManager;
    }

    @Override
    public double fetchPercentageFromExternalService() {
        try {
            return percentageClient.fetchPercentageFromExternalService();
        } catch (Exception ex) {
            Cache cache = cacheManager.getCache("percentage");
            if (cache != null) {
                Double cachedValue = cache.get("getPercentage", Double.class);
                if (cachedValue != null) {
                    return cachedValue;
                }
            }
            return 0.1;
        }
    }
}
