package com.github.mediator.externalclients.github.model.contributor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubWeek {
    @JsonProperty("w")
    private long w;

    @JsonProperty("c")
    private long c;

    @JsonProperty("a")
    private long a;

    @JsonProperty("d")
    private long d;
}
