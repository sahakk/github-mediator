package com.github.mediator.domain.project;

import com.github.mediator.domain.model.project.Project;
import com.github.mediator.domain.service.project.ProjectServiceImpl;
import com.github.mediator.respository.ProjectRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepositoryImpl projectRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void findProjects_whenTermIsNull() {
        assertThrows(IllegalArgumentException.class, () -> projectService.findProjects(null));
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void findProjects_whenTermIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> projectService.findProjects(""));
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    void findProjects_whenTermIsValid() {
        final String term = "term";
        List<Project> projects = createProjects();
        when(projectRepository.findProjects(eq(term))).thenReturn(projects);

        List<Project> result = projectService.findProjects(term);

        assertThat(result).containsExactlyInAnyOrderElementsOf(projects);
        verifyNoMoreInteractions(projectRepository);
    }

    private List<Project> createProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(createProject());
        return projects;
    }

    private Project createProject() {
        return new Project();
    }

}
