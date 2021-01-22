package com.github.mediator.domain.service.commit;

import static org.springframework.util.Assert.hasText;

import com.github.mediator.domain.model.commit.Commit;
import com.github.mediator.respository.CommitRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommitServiceImpl implements CommitService {

    private final CommitRepository commitRepository;

    @Override
    public List<Commit> findCommits(String owner, String repo) {
        validateInputs(owner, repo);
        return commitRepository.findCommits(owner, repo);
    }

    private void validateInputs(String owner, String repo) {
        hasText(owner, "Owner should be provided");
        hasText(repo, "Repo should be provided");
    }
}
