package com.github.mediator.externalclients.github.model.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubProject {
    private Long id;
    private String name;
    private String fullName;
    private GithubProjectOwner owner;
}
