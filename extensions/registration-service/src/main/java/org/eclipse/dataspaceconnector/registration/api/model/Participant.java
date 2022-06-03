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

    /**
     * Constructs a new instance of {@link Participant}
     *
     * @param name                  name of the participant
     * @param targetUrl             url of the participant EDC instance
     * @param supportedProtocols    protocols supported by the participant
     */
    @JsonCreator
    public Participant(@JsonProperty("name") String name, @JsonProperty("url") String targetUrl, @JsonProperty("supportedProtocols") List<String> supportedProtocols) {
        this.name = name;
        this.targetUrl = targetUrl;
        this.supportedProtocols = supportedProtocols;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public List<String> getSupportedProtocols() {
        return supportedProtocols;
    }

    public String getName() {
        return name;
    }
}
