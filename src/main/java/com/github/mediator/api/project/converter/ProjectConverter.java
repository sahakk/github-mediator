package com.github.mediator.api.project.converter;

import com.github.mediator.api.project.dto.ProjectDto;
import com.github.mediator.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectConverter {

    private final ProjectOwnerConverter projectOwnerConverter;

    public ProjectDto convert(final Project project) {
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .fullName(project.getFullName())
                .owner(projectOwnerConverter.convert(project.getOwner()))
                .build();
    }

    public List<ProjectDto> convert(final List<Project> projects) {
        if(CollectionUtils.isEmpty(projects)) {
            return new ArrayList<>();
        }
        return projects.stream().map(this::convert).collect(Collectors.toList());
    }

}
