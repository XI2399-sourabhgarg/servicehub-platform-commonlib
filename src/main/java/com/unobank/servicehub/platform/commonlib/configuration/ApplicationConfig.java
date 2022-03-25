package com.unobank.servicehub.platform.commonlib.configuration;


import com.unobank.servicehub.platform.commonlib.client.ApiClient;
import com.unobank.servicehub.platform.commonlib.client.RestTemplateApiClient;
import com.unobank.servicehub.platform.commonlib.interceptor.RequestHeaderInterceptor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/** @author ankur.goel */
@Data
@NoArgsConstructor
@Configuration
@EnableRetry
public class ApplicationConfig {

  @Autowired RestTemplateBuilder restTemplateBuilder;

  @Autowired
  RequestHeaderInterceptor requestHeaderInterceptor;

  @Bean
  public ApiClient client() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    return new RestTemplateApiClient(sslDisableRestTemplate(),disableRestTemplate());
  }

  @Bean
  public RestTemplate sslDisableRestTemplate()
          throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    RestTemplate restTemplate = sslDisabledRestTemplate();
    return addIdempotencyInterceptor(restTemplate);
  }

  @Bean
  public RestTemplate disableRestTemplate()
          throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    return sslDisabledRestTemplate();
  }

  private RestTemplate sslDisabledRestTemplate()
          throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
    SSLContext sslContext =
            SSLContexts.custom().loadTrustMaterial(null, TrustAllStrategy.INSTANCE).build();

    SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
    RestTemplate restTemplate = restTemplateBuilder.requestFactory(() -> new
            HttpComponentsClientHttpRequestFactory(httpClient)).build();
    return restTemplate;
  }

  private RestTemplate addIdempotencyInterceptor(RestTemplate restTemplate) {
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(requestHeaderInterceptor);
    restTemplate.setInterceptors(interceptors);
    return restTemplate;
  }
}