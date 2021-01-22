package com.github.mediator.externalclients.github.model.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubProjectResponse {

    @JsonProperty("items")
    List<GitHubProject> gitHubProjects;

    public GitHubProjectResponse() {
    }

    public List<GitHubProject> getGitHubProjects() {
        return gitHubProjects;
    }

    public void setGitHubProjects(List<GitHubProject> gitHubProjects) {
        this.gitHubProjects = gitHubProjects;
    }
}
