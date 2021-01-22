package com.github.mediator.externalclients.github.model.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubCommit {
    @JsonProperty("message")
    private String message;

    @JsonProperty("author")
    private GitHubAuthor author;
}
