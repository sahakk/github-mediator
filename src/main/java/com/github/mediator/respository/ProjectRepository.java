package com.github.mediator.respository;

import com.github.mediator.domain.model.project.Project;
import java.util.List;

public interface ProjectRepository {
    List<Project> findProjects(final String term);
}
