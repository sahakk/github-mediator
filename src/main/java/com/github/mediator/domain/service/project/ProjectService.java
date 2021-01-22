package com.github.mediator.domain.service.project;

import com.github.mediator.domain.model.project.Project;

import java.util.List;

public interface ProjectService {
    List<Project> findProjects(String term);
}
