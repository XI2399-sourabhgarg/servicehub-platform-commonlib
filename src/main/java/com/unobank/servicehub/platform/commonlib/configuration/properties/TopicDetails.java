package com.unobank.servicehub.platform.commonlib.configuration.properties;

import lombok.Data;

/**
 * @author tanay sen
 */
@Data
public class TopicDetails {
    private String name;
    private int numOfPartition;
    private int replicationFactor;

}
