package com.github.mediator.respository;

import com.github.mediator.domain.model.contributor.Contributor;
import com.github.mediator.domain.model.contributor.ContributorAuthor;
import com.github.mediator.domain.model.contributor.Week;
import com.github.mediator.externalclients.github.client.GithubClient;
import com.github.mediator.externalclients.github.model.contributor.GitHubContributor;
import com.github.mediator.externalclients.github.model.contributor.GitHubContributorAuthor;
import com.github.mediator.externalclients.github.model.contributor.GitHubWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@RequiredArgsConstructor
public class ContributorRepositoryImpl implements ContributorRepository {

    private final GithubClient githubClient;

    @Override
    public List<Contributor> findContributors(final String owner,
                                              final String project) {
        final List<GitHubContributor> githubCommitNodes = githubClient
            .getContributors(owner, project);
        return convertList(githubCommitNodes);
    }

    private List<Contributor> convertList(
        List<GitHubContributor> githubContributors) {
        if (CollectionUtils.isEmpty(githubContributors)) {
            return new ArrayList<>();
        }
        return githubContributors.stream().map(this::convert)
            .collect(Collectors.toList());
    }

    private Contributor convert(GitHubContributor gitHubContributor) {
        return Contributor.builder()
            .total(gitHubContributor.getTotal())
            .weeks(extractWeeks(gitHubContributor.getWeeks()))
            .author(extractAuthor(gitHubContributor.getAuthor()))
            .build();
    }

    private List<Week> extractWeeks(final GitHubWeek[] githubWeeks) {
        if (githubWeeks == null || githubWeeks.length == 0) {
            return null;
        }
        List<Week> weeks = new ArrayList<>(githubWeeks.length);
        for (GitHubWeek githubWeek : githubWeeks) {
            weeks.add(convertWeek(githubWeek));
        }
        return weeks;
    }

    private ContributorAuthor extractAuthor(final GitHubContributorAuthor githubContributorAuthor) {
        if (githubContributorAuthor == null) {
            return null;
        }
        final ContributorAuthor author = new ContributorAuthor();
        author.setId(githubContributorAuthor.getId());
        author.setLogin(githubContributorAuthor.getLogin());
        return  author;
    }

    private Week convertWeek(final GitHubWeek githubWeek) {
        final Week week = new Week();
        week.setA(githubWeek.getA());
        week.setC(githubWeek.getC());
        week.setD(githubWeek.getD());
        week.setW(githubWeek.getW());
        return week;
    }
}
