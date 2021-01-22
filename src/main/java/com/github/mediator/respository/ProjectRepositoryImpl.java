package com.github.mediator.respository;

import com.github.mediator.domain.model.project.Project;
import com.github.mediator.domain.model.project.ProjectOwner;
import com.github.mediator.externalclients.github.client.GithubClient;
import com.github.mediator.externalclients.github.model.project.GitHubProject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final GithubClient githubClient;

    @Override
    public List<Project> findProjects(final String term) {
        return githubClient.getProjects(term).stream().map(this::toProject).collect(Collectors.toList());
    }

    private Project toProject(GitHubProject gitHubProject) {
        final Project project = new Project();
        project.setId(gitHubProject.getId());
        project.setName(gitHubProject.getName());
        project.setFullName(gitHubProject.getFullName());
        project.setOwner(new ProjectOwner(gitHubProject.getOwner().getId(), gitHubProject.getOwner().getLogin()));
        return project;
    }

}
