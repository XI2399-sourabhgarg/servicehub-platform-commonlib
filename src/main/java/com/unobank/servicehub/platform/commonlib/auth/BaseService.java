
package com.unobank.servicehub.platform.commonlib.auth;


import com.unobank.servicehub.platform.commonlib.configuration.properties.MambuApiProperties;
import com.unobank.servicehub.platform.commonlib.constant.MambuConstants;
import com.unobank.servicehub.platform.commonlib.dto.auth.OAuthToken;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@NoArgsConstructor
public class BaseService {

    @Autowired
    MambuApiProperties mambuApiProperties;

    @Autowired
    AuthService authService;


    public BaseService(MambuApiProperties mambuApiProperties, AuthService authService) {
        this.mambuApiProperties = mambuApiProperties;
        this.authService = authService;
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        OAuthToken authToken = authService.getAuthToken();
        headers.add(
                MambuConstants.AUTHORIZATION,
                authToken.getTokenType() + MambuConstants.EMPTY_SPACE + authToken.getAccessToken());
        headers.add(MambuConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    public HttpHeaders getHttpHeadersWithCorrelation() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(MambuConstants.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.add(MambuConstants.CORRELATION_ID_HEADER_NAME, MDC.get("correlationId"));
        return headers;
    }

    public HttpHeaders getHttpHeaders(String idempotencyKey) {
        HttpHeaders headers = getHttpHeaders();
        if (!idempotencyKey.isEmpty()) headers.add(MambuConstants.IDEMPOTANCY_KEY, idempotencyKey);
        return headers;
    }

    public String getUri(final String requestParamPath, final MultiValueMap<String, String> queryParamMap) {
        String url = new StringBuilder().append(mambuApiProperties.getUri().toString()).append(requestParamPath).toString();
        String uri = UriComponentsBuilder.fromUriString(url).queryParams(queryParamMap).toUriString();
        return uri;
    }
}


