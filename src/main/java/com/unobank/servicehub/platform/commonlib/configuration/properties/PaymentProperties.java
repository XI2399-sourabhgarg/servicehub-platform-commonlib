package com.unobank.servicehub.platform.commonlib.configuration.properties;

import com.unobank.servicehub.platform.commonlib.configuration.PaymentGlAccount;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author tanay sen
 */
@Component
@ConfigurationProperties(prefix = "channel-entries")
@Data
public class PaymentProperties {
    private Map<String, PaymentGlAccount> channelCode;

    public PaymentGlAccount getGLAccount(String channelCode) {
        return this.channelCode.get(channelCode);
    }

    public boolean isValid(String channelCode) {
        return Objects.nonNull(this.channelCode.get(channelCode));
    }
}
