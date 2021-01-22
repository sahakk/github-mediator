package com.github.mediator.externalclients.github.model.commit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GitHubCommitResponse {
    List<GitHubCommitNode> gitHubCommitNodes;

    public List<GitHubCommitNode> getGitHubCommits() {
        return gitHubCommitNodes;
    }

    public void setGitHubCommits(List<GitHubCommitNode> gitHubCommitNodes) {
        this.gitHubCommitNodes = gitHubCommitNodes;
    }
}
