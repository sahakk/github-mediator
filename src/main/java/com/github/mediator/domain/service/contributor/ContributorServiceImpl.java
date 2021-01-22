package com.github.mediator.domain.service.contributor;

import static org.springframework.util.Assert.hasText;

import com.github.mediator.domain.model.contributor.Contributor;
import com.github.mediator.respository.ContributorRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContributorServiceImpl implements ContributorService {

    private final ContributorRepository contributorRepository;

    @Override
    public List<Contributor> findContributors(final String owner,
                                              final String project) {
        validateInputs(owner, project);
        return contributorRepository.findContributors(owner, project);
    }

    private void validateInputs(final String owner, final String project) {
        hasText(owner, "Owner should not be blank");
        hasText(project, "Repo/Project should not be blank");
    }
}
