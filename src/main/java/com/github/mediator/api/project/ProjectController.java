package com.github.mediator.api.project;

import com.github.mediator.api.project.converter.ProjectConverter;
import com.github.mediator.api.project.dto.ProjectDto;
import com.github.mediator.domain.model.project.Project;
import com.github.mediator.domain.service.project.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectConverter projectConverter;

    public ProjectController(final ProjectService projectService,
                             final ProjectConverter projectConverter) {
        this.projectService = projectService;
        this.projectConverter = projectConverter;
    }

    @RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<ProjectDto>> findProject(@RequestParam String term) {
        final List<Project> projects = projectService.findProjects(term);
        return ResponseEntity.ok(projectConverter.convert(projects));
    }
}
