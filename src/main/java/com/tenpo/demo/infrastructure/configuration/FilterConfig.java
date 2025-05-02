package com.tenpo.demo.infrastructure.configuration;

import com.tenpo.demo.application.service.IApiCallHistoryService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    private final IApiCallHistoryService apiCallHistoryService;

    public FilterConfig(IApiCallHistoryService apiCallHistoryService) {
        this.apiCallHistoryService = apiCallHistoryService;
    }

    @Bean
    public FilterRegistrationBean<ApiCallHistoryFilter> apiCallHistoryFilter() {
        FilterRegistrationBean<ApiCallHistoryFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiCallHistoryFilter(apiCallHistoryService));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
