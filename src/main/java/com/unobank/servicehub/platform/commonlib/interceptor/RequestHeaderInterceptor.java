package com.unobank.servicehub.platform.commonlib.interceptor;

import com.unobank.servicehub.platform.commonlib.constant.MambuConstants;
import com.unobank.servicehub.platform.commonlib.exception.InvalidHeaderFieldException;
import com.unobank.servicehub.platform.commonlib.util.ApplicationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;

@Component
public class RequestHeaderInterceptor implements HandlerInterceptor, ClientHttpRequestInterceptor {

    private String headerName;

    private String headerValue;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (StringUtils.isBlank(request.getHeader(MambuConstants.IDEMPOTENCY_KEY)) && StringUtils.isBlank(request.getHeader(MambuConstants.IDEMPOTENCY_KEY))) {
            throw new InvalidHeaderFieldException(
                    MessageFormat.format(
                            "Invalid Request : {0} is not part of request headers",
                            MambuConstants.IDEMPOTENCY_KEY));
        }
        final String correlationId = getCorrelationIdFromHeader(request);
        MDC.put(MambuConstants.CORRELATION_ID_LOG_VAR_NAME, correlationId);
        this.headerName = MambuConstants.IDEMPOTENCY_KEY;
        this.headerValue = request.getHeader(MambuConstants.IDEMPOTENCY_KEY);
        return true;
    }

    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) throws Exception {
        MDC.remove(MambuConstants.CORRELATION_ID_LOG_VAR_NAME);
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (StringUtils.isNotBlank(headerValue))
            request.getHeaders().set(headerName, headerValue);
        return execution.execute(request, body);
    }

    private String getCorrelationIdFromHeader(final HttpServletRequest request) {
        String correlationId = request.getHeader(MambuConstants.CORRELATION_ID_HEADER_NAME);
        if (StringUtils.isBlank(correlationId)) {
            correlationId = generateUniqueCorrelationId();
        }
        return correlationId;
    }

    private String generateUniqueCorrelationId() {
        return ApplicationUtil.getUniqueProcessId();
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }
}

