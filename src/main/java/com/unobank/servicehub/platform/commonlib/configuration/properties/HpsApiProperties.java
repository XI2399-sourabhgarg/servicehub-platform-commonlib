package com.unobank.servicehub.platform.commonlib.configuration.properties;

import com.unobank.servicehub.platform.commonlib.constant.MambuConstants;
import lombok.Data;
import lombok.Synchronized;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author ankur.goel
 */
@Component
@ConfigurationProperties(prefix = "hps-api.uri")
@Data
public class HpsApiProperties {

    private String protocol;
    private String host;
    private String port;
    private String path;


    public String getUrl() {
        String url = null;
        if (StringUtils.isBlank(getPort()) || "80".equals(getPort())) {
            url = getProtocol() + MambuConstants.PROTOCOL_HOST_CONNECTOR + getHost();
        } else {
            url = getProtocol() + MambuConstants.PROTOCOL_HOST_CONNECTOR + getHost() + MambuConstants.COLON + getPort();
        }
        return url;
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
}
