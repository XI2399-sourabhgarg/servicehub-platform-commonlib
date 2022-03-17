package com.unobank.servicehub.platform.commonlib.dto.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

/** @author ankur.goel */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OAuthToken implements Serializable {

  private String accessToken;
  private Long expiresIn;
  private String tokenType;
  private long creationTimestamp;

  public OAuthToken() {
    this.creationTimestamp = Instant.now().toEpochMilli();
  }

  @JsonIgnore
  public boolean isExpired() {
    long currentTime = Instant.now().toEpochMilli();
    //setExpiresIn(getExpiresIn() - (currentTime - creationTimestamp));
    Long fiveMinutesBeforeExpired = creationTimestamp + getExpiresIn() - 300000;
    return currentTime > fiveMinutesBeforeExpired;
  }
}
