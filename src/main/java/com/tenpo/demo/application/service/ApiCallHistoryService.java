package com.tenpo.demo.application.service;


import com.tenpo.demo.domain.ApiCallHistory;
import com.tenpo.demo.domain.port.IPersistence;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiCallHistoryService implements IApiCallHistoryService {

    private final IPersistence<ApiCallHistory, Long> apiCallHistoryRepository;

    public ApiCallHistoryService(IPersistence<ApiCallHistory, Long>  apiCallHistoryRepository) {
        this.apiCallHistoryRepository = apiCallHistoryRepository;
    }

    @Override
    @Async
    public void saveApiCallHistory(ApiCallHistory apiCallHistory) {
        apiCallHistoryRepository.save(apiCallHistory);
    }

    @Override
    public List<ApiCallHistory> getAllApiCallHistory() {
        return apiCallHistoryRepository.findAll();
    }
}
