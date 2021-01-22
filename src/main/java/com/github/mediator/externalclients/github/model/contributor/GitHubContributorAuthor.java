package com.github.mediator.externalclients.github.model.contributor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubContributorAuthor {
    @JsonProperty("id")
    private long id;

    @JsonProperty("login")
    private String login;

    @JsonProperty("type")
    private String userType;

    @JsonProperty("site_admin")
    private Boolean isSiteAdmin;
}
