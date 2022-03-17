package com.unobank.servicehub.platform.commonlib.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
public class ApiExecutionTimeFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExecutionTimeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // empty
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Instant start = Instant.now();
        try {
            chain.doFilter(req, resp);
        } finally {
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            if(!((HttpServletRequest) req).getRequestURI().equals("/"))
            log.info("{}: {} ms ", ((HttpServletRequest) req).getRequestURI(),  time);
        }
    }

    @Override
    public void destroy() {
        // empty
    }
}
