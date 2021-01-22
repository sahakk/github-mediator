package com.github.mediator.api.project;

import com.github.mediator.api.project.converter.ProjectConverter;
import com.github.mediator.api.project.converter.ProjectOwnerConverter;
import com.github.mediator.api.project.dto.ProjectDto;
import com.github.mediator.api.project.dto.ProjectOwnerDto;
import com.github.mediator.domain.model.project.Project;
import com.github.mediator.domain.model.project.ProjectOwner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectConverterTest {

    @Mock
    private ProjectOwnerConverter projectOwnerConverter;

    @InjectMocks
    private ProjectConverter projectConverter;

    @Test
    void convert_whenProjectIsValid() {
        Project project = createProject();
        ProjectOwnerDto projectOwnerDto = createProjectOwnerDto();
        when(projectOwnerConverter.convert(eq(project.getOwner()))).thenReturn(projectOwnerDto);
        ProjectDto result = projectConverter.convert(project);
        assertThat(result.getId()).isEqualTo(project.getId());
        assertThat(result.getName()).isEqualTo(project.getName());
        assertThat(result.getFullName()).isEqualTo(project.getFullName());
    }

    private ProjectOwnerDto createProjectOwnerDto() {
        return new ProjectOwnerDto(10L, "name");
    }

    private Project createProject() {
        ProjectOwner projectOwner = createProjectOwner();
        return new Project(1L, "project_name", "project_full_name", projectOwner);
    }

    private ProjectOwner createProjectOwner() {
        return new ProjectOwner(1L, "name");
    }

}
