package com.github.mediator.externalclients.github.model.contributor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubContributor {
    @JsonProperty("total")
    private int total;

    @JsonProperty("weeks")
    private GitHubWeek[] weeks;

    @JsonProperty("author")
    private GitHubContributorAuthor author;
}
