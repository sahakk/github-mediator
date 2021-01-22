package com.github.mediator.externalclients.github.model.commit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GitHubCommitNode {
    @JsonProperty("sha")
    private String sha;

    @JsonProperty("commit")
    private GitHubCommit githubCommit;
}
