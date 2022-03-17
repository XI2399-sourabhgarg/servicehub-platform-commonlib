package com.unobank.servicehub.platform.commonlib.configuration.properties;



import com.unobank.servicehub.platform.commonlib.constant.MambuConstants;
import lombok.Data;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author ankur.goel
 */
@Component
@ConfigurationProperties(prefix = "mambu-api.auth")
@Data
@Slf4j
public class ApigeeAuthApiProperties {

    private String protocol;
    private String host;
    private String path;
    private String port;
    private String grantType;
    private String clientId;
    private String clientSecret;

    public URI getAuthenticationUri() {
        URI uri = UriComponentsBuilder.fromHttpUrl(getUrl()).path(getPath()).build().toUri();
        log.info("Auth URI {}", uri.toString());
        return uri;
    }

    public String getUrl() {
        String url = null;
        if (StringUtils.isBlank(getPort()) || "80".equals(getPort())) {
            url = getProtocol() + MambuConstants.PROTOCOL_HOST_CONNECTOR + getHost();
        } else {
            url = getProtocol() + MambuConstants.PROTOCOL_HOST_CONNECTOR + getHost() + MambuConstants.COLON + getPort();
        }
        return url;
    }

    public MultiValueMap<String, String> getAuthenticationRequestBody() {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(MambuConstants.GRANT_TYPE, grantType);
        requestBody.add(MambuConstants.CLIENT_ID, clientId);
        requestBody.add(MambuConstants.CLIENT_SECRET, clientSecret);
        return requestBody;
    }

    @Synchronized
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Synchronized
    public void setHost(String host) {
        this.host = host;
    }

    @Synchronized
    public void setPath(String path) {
        this.path = path;
    }

    @Synchronized
    public void setPort(String port) {
        this.port = port;
    }

    @Synchronized
    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    @Synchronized
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Synchronized
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
