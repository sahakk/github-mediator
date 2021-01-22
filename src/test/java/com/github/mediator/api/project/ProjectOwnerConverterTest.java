package com.github.mediator.api.project;

import com.github.mediator.api.project.converter.ProjectOwnerConverter;
import com.github.mediator.api.project.dto.ProjectOwnerDto;
import com.github.mediator.domain.model.project.ProjectOwner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProjectOwnerConverterTest {

    @InjectMocks
    private ProjectOwnerConverter projectOwnerConverter;

    @Test
    void convert_whenArgumentIsNull() {
        ProjectOwnerDto result = projectOwnerConverter.convert(null);
        assertThat(result).isNull();
    }

    @Test
    void convert_whenArgumentIsValid() {
        ProjectOwner projectOwner = createProjectOwner();
        final ProjectOwnerDto result = projectOwnerConverter.convert(projectOwner);
        assertThat(result.getId()).isEqualTo(projectOwner.getId());
        assertThat(result.getName()).isEqualTo(projectOwner.getName());
    }

    private ProjectOwner createProjectOwner() {
        return new ProjectOwner(1L, "name");
    }
}
