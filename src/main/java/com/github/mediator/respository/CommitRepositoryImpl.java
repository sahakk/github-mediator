package com.github.mediator.respository;

import com.github.mediator.domain.model.commit.Commit;
import com.github.mediator.externalclients.github.client.GithubClient;
import com.github.mediator.externalclients.github.model.commit.GitHubCommit;
import com.github.mediator.externalclients.github.model.commit.GitHubCommitNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@RequiredArgsConstructor
public class CommitRepositoryImpl implements CommitRepository {

    private final GithubClient githubClient;

    @Override
    public List<Commit> findCommits(String owner, String repo) {
        final List<GitHubCommitNode> githubCommitNodes = githubClient.getCommits(owner, repo);
        return convertList(githubCommitNodes);
    }

    private List<Commit> convertList(List<GitHubCommitNode> githubCommitNodes) {
        if (CollectionUtils.isEmpty(githubCommitNodes)) {
            return new ArrayList<>();
        }
        return githubCommitNodes.stream().map(this::convert).collect(Collectors.toList());
    }

    private Commit convert(GitHubCommitNode gitHubCommitNode) {
        final Commit commit = new Commit();
        commit.setSha(gitHubCommitNode.getSha());
        commit.setAuthor(extractAuthor(gitHubCommitNode));
        commit.setCommitDate(extractDate(gitHubCommitNode));
        commit.setMessage(extractMessage(gitHubCommitNode));
        return commit;
    }

    private String extractDate(GitHubCommitNode gitHubCommitNode) {
        if (isAuthorNodeBlank(gitHubCommitNode)) {
            return StringUtils.EMPTY;
        }
        return gitHubCommitNode.getGithubCommit().getAuthor().getDate();
    }

    private String extractAuthor(GitHubCommitNode gitHubCommitNode) {
        if (isAuthorNodeBlank(gitHubCommitNode)) {
            return StringUtils.EMPTY;
        }
        return gitHubCommitNode.getGithubCommit().getAuthor().getName();
    }

    private String extractMessage(GitHubCommitNode gitHubCommitNode) {
        if (isMessageHolderBlank(gitHubCommitNode)) {
            return StringUtils.EMPTY;
        }
        return gitHubCommitNode.getGithubCommit().getMessage();
    }

    private boolean isAuthorNodeBlank(GitHubCommitNode gitHubCommitNode) {
        if (gitHubCommitNode == null) {
            return true;
        }
        Optional<GitHubCommit> commitHolder = Optional.ofNullable(gitHubCommitNode.getGithubCommit());
        return !(commitHolder.isPresent() && commitHolder.get().getAuthor() != null);
    }

    private boolean isMessageHolderBlank(GitHubCommitNode gitHubCommitNode) {
        if (gitHubCommitNode == null) {
            return true;
        }
        Optional<GitHubCommit> commitHolder = Optional.ofNullable(gitHubCommitNode.getGithubCommit());
        return !(commitHolder.isPresent() && StringUtils.isNotBlank(commitHolder.get().getMessage()));
    }
}
