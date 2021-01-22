package com.github.mediator.externalclients.github.client;

import com.github.mediator.externalclients.github.model.commit.GitHubCommitNode;
import com.github.mediator.externalclients.github.model.contributor.GitHubContributor;
import com.github.mediator.externalclients.github.model.project.GitHubProject;
import com.github.mediator.externalclients.github.model.project.GitHubProjectResponse;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

    private static final String ROUTE_REPOSITORIES = "/search/repositories?q=";
    private static final String ROUTE_CONTRIBUTORS = "/repos/{owner}/{repo}/stats/contributors";
    private static final String ROUTE_COMMITS = "/repos/{owner}/{repo}/commits";

    private final RestTemplate restTemplate;

    @Value("${github.base.url}")
    private String baseUrl;

    public GithubClient(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<GitHubProject> getProjects(final String term) {
        final ResponseEntity<GitHubProjectResponse> response = restTemplate
            .getForEntity(constructRepositoriesGetRequestUri(term),
                          GitHubProjectResponse.class);
        return Objects.requireNonNull(response.getBody()).getGitHubProjects();
    }

    public List<GitHubContributor> getContributors(final String owner,
                                                   final String project) {
        final ResponseEntity<GitHubContributor[]> response = restTemplate
            .getForEntity(constructContributorGetRequestUri(owner, project),
                          GitHubContributor[].class);
        final GitHubContributor[] body = response.getBody();
        return Arrays.asList(Objects.requireNonNull(body));
    }

    public List<GitHubCommitNode> getCommits(final String owner,
                                             final String project) {
        final ResponseEntity<GitHubCommitNode[]> response = restTemplate
            .getForEntity(constructCommitGetRequestUri(owner, project),
                          GitHubCommitNode[].class);
        final GitHubCommitNode[] body = response.getBody();
        return Arrays.asList(Objects.requireNonNull(body));
    }

    private URI constructRepositoriesGetRequestUri(final String term) {
        return URI.create(baseUrl + ROUTE_REPOSITORIES + term);
    }

    private URI constructContributorGetRequestUri(final String owner,
                                                  final String project) {
        return URI.create(
            baseUrl + ROUTE_CONTRIBUTORS.replaceAll("\\{owner\\}", owner)
                .replaceAll("\\{repo\\}", project));
    }

    private URI constructCommitGetRequestUri(final String owner,
                                             final String project) {
        return URI.create(
            baseUrl + ROUTE_COMMITS.replaceAll("\\{owner\\}", owner)
                .replaceAll("\\{repo\\}", project));
    }
}
