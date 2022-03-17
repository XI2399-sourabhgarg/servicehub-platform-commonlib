
package com.unobank.servicehub.platform.commonlib.auth;



import com.unobank.servicehub.platform.commonlib.configuration.properties.ApigeeAuthApiProperties;
import com.unobank.servicehub.platform.commonlib.dto.auth.OAuthToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;


/** @author ankur.goel */

@Service
@Slf4j
public class AuthService {

  private final Object lockObject = new Object();
  @Autowired private RestTemplate disableRestTemplate;
  @Autowired private ApigeeAuthApiProperties mambuAuthApiProperties;
  private OAuthToken authToken;

  public AuthService(
      RestTemplate disableRestTemplate,
      ApigeeAuthApiProperties mambuAuthApiProperties) {
    this.disableRestTemplate = disableRestTemplate;
    this.mambuAuthApiProperties = mambuAuthApiProperties;
  }

  /*
public OAuthToken getAuthToken() {
      log.info("calling auth api to get OAuthToken");

      OAuthToken result = authToken;

      if(result == null || result.isExpired()) {

          synchronized (lockObject) {

              result = authToken;

                  if (result == null || result.isExpired()) {
                      log.info("OauthToken missing or expired, retrieving new token, old OAuthToken: {}", authToken);
                      authToken = executeRest();
                      //expiration times given in sec, but converting it to millis to make math easier
                      authToken.setExpiresIn(authToken.getExpiresIn() * 1000);
                      log.debug("OAuthToken fetched, new OAuthToken : {}", authToken);
                      log.info("OAuthToken successfully fetched");
                  } else {
                      log.debug("OAuthToken is already set, current OAuthToken : {}", authToken);
                  }
               }
           } else {
              log.debug("OAuthToken still good, reusing" + authToken);
              log.info("OAuthToken still good, reusing");
         }
          return authToken;
  }*/


  @Retryable(ResourceAccessException.class)
  public OAuthToken getAuthToken() {
    log.info("Retrying calling auth api to get OAuthToken count {}", RetrySynchronizationManager.getContext().getRetryCount());
    OAuthToken token = executeRest();
    log.info("OAuthToken successfully fetched");
    return token;
  }

  private OAuthToken executeRest() {
    ResponseEntity<OAuthToken> oAuthToken = null;
    try {
      oAuthToken = disableRestTemplate.exchange(getAuthRequestEntity(), OAuthToken.class);
    } catch (HttpClientErrorException e) {
      log.error("error getting authentication token", e);
      throw e;
    }
    return oAuthToken.getBody();
  }

  private RequestEntity getAuthRequestEntity() {
    return RequestEntity.post(mambuAuthApiProperties.getAuthenticationUri())
        .contentType(MediaType.APPLICATION_JSON)
        .body(mambuAuthApiProperties.getAuthenticationRequestBody().toSingleValueMap());
  }
}

