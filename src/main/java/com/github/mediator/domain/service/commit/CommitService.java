package com.github.mediator.domain.service.commit;

import com.github.mediator.domain.model.commit.Commit;
import java.util.List;

public interface CommitService {
    List<Commit> findCommits(String owner, String repo);
}
