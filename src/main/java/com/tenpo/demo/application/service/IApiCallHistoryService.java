package com.tenpo.demo.application.service;

import com.tenpo.demo.domain.ApiCallHistory;

import java.util.List;

public interface IApiCallHistoryService {
    void saveApiCallHistory(ApiCallHistory apiCallHistory);
    List<ApiCallHistory> getAllApiCallHistory();

}
