package com.tenpo.demo.infrastructure.configuration;

import com.tenpo.demo.application.service.IApiCallHistoryService;
import com.tenpo.demo.domain.ApiCallHistory;
import com.tenpo.demo.domain.exceptions.ApiExceptionHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

public class ApiCallHistoryFilter extends OncePerRequestFilter {

    private final IApiCallHistoryService apiCallHistoryService;

    public ApiCallHistoryFilter(IApiCallHistoryService apiCallHistoryService) {
        this.apiCallHistoryService = apiCallHistoryService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(response);
        String params = request.getQueryString();

        try {
            filterChain.doFilter(request, wrappedResponse);
            String body = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());
            apiCallHistoryService.saveApiCallHistory(
                     ApiCallHistory.builder()
                             .endpoint(request.getRequestURI())
                             .timestamp(java.time.LocalDateTime.now())
                             .parameters(params != null ? params : "")
                             .response(body)
                             .error(true)
                             .build()
            );
        } catch (Exception ex) {
            apiCallHistoryService.saveApiCallHistory(
                    ApiCallHistory.builder()
                            .endpoint(request.getRequestURI())
                            .timestamp(java.time.LocalDateTime.now())
                            .parameters(params != null ? params : "")
                            .response(ex.getMessage())
                            .error(true)
                            .build()
            );
            throw new ApiExceptionHandler(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        } finally {
            wrappedResponse.copyBodyToResponse();
        }
    }
}
