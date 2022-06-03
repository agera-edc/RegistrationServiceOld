package org.eclipse.dataspaceconnector.registration.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Dataspace participant.
 */
public class Participant {
    @JsonProperty("name")
    private final String name;
    @JsonProperty("url")
    private final String targetUrl;
    @JsonProperty("supportedProtocols")
    private final List<String> supportedProtocols;

    @JsonCreator
    public Participant(@JsonProperty("name") String name, @JsonProperty("url") String targetUrl, @JsonProperty("supportedProtocols") List<String> supportedProtocols) {
        this.name = name;
        this.targetUrl = targetUrl;
        this.supportedProtocols = supportedProtocols;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }

    public List<String> getSupportedProtocols() {
        return this.supportedProtocols;
    }

    public String getName() {
        return this.name;
    }
}
