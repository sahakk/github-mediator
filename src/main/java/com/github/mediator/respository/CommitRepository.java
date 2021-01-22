package com.github.mediator.respository;

import com.github.mediator.domain.model.commit.Commit;
import java.util.List;

public interface CommitRepository {

    List<Commit> findCommits(String owner, String repo);
}
