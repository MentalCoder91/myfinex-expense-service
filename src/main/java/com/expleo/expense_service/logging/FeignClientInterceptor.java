package com.expleo.expense_service.logging;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String CORRELATION_ID_HEADER = "Correlation-Id";


    @Override
    public void apply(RequestTemplate template) {

        String correlationId = MDC.get("Correlation-Id");
        if (correlationId != null) {
            template.header(CORRELATION_ID_HEADER, correlationId);
        }
    }
}
