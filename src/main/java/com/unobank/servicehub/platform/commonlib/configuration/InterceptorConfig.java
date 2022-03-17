package com.unobank.servicehub.platform.commonlib.configuration;

import com.unobank.servicehub.platform.commonlib.interceptor.RequestHeaderInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Autowired
  private RequestHeaderInterceptor requestHeaderInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
            .addInterceptor(requestHeaderInterceptor);
  }
}
