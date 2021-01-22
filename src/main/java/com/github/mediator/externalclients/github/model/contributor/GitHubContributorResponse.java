package com.github.mediator.externalclients.github.model.contributor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.mediator.externalclients.github.model.commit.GitHubCommitNode;
import java.util.List;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubContributorResponse {
    List<GitHubContributor> gitHubContributors;
}
