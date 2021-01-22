package com.github.mediator.api.project;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.mediator.api.project.converter.ProjectConverter;
import com.github.mediator.api.project.dto.ProjectDto;
import com.github.mediator.api.project.dto.ProjectOwnerDto;
import com.github.mediator.domain.model.project.Project;
import com.github.mediator.domain.model.project.ProjectOwner;
import com.github.mediator.domain.service.project.ProjectService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ProjectController.class)
public class ProjectControllerTest {

    private static final String ROOT_PATH = "/projects";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectConverter projectConverter;

    @MockBean
    private ProjectService projectService;

    @Test
    void givenPaginationRequest_whenGettingAll_thenOk() throws Exception {
        // Prepare data
        final List<Project> projects = createProjects(1);
        final String term = "term";
        // Expectations
        when(projectService.findProjects(eq(term))).thenReturn(projects);
        when(projectConverter.convert(isA(List.class)))
            .thenReturn(Collections.singletonList(createProjectDto()));

        // Run tests scenario
        mockMvc.perform(get(prependRootPath(String.format("?term=%s", term))))
            .andExpect(status().isOk());

        // Verify
        verify(projectService).findProjects(eq(term));
        verify(projectConverter).convert(isA(List.class));
        verifyAllNoMoreInteractions();
    }

    private void verifyAllNoMoreInteractions() {
        verifyNoMoreInteractions(projectService);
        verifyNoMoreInteractions(projectConverter);
    }

    private List<Project> createProjects(int size) {
        return IntStream.range(0, size).mapToObj(it -> createProject())
            .collect(Collectors.toList());
    }

    private Project createProject() {
        ProjectOwner projectOwner = createProjectOwner();
        return new Project(1L, "project_name", "project_full_name",
            projectOwner);
    }

    private ProjectOwner createProjectOwner() {
        return new ProjectOwner(1L, "name");
    }

    private ProjectDto createProjectDto() {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setName("name");
        projectDto.setFullName("full_name");
        projectDto.setOwner(createProjectOwnerDto());
        return projectDto;
    }

    private ProjectOwnerDto createProjectOwnerDto() {
        return new ProjectOwnerDto(10L, "name");
    }

    private String prependRootPath(final String path) {
        return ROOT_PATH + path;
    }
}
