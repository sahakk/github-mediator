package com.github.mediator.domain.service.contributor;

import com.github.mediator.domain.model.contributor.Contributor;
import java.util.List;

public interface ContributorService {
    List<Contributor> findContributors(final String owner, final String project);
}
