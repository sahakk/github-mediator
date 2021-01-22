package com.github.mediator.respository;

import com.github.mediator.domain.model.contributor.Contributor;
import java.util.List;

public interface ContributorRepository {
    List<Contributor> findContributors(String owner, String project);
}
