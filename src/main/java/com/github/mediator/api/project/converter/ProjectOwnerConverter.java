package com.github.mediator.api.project.converter;

import com.github.mediator.api.project.dto.ProjectOwnerDto;
import com.github.mediator.domain.model.project.ProjectOwner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProjectOwnerConverter {

    public ProjectOwnerDto convert(final ProjectOwner owner) {
        return Optional.ofNullable(owner)
                .map(it -> new ProjectOwnerDto(it.getId(), it.getName()))
                .orElse(null);
    }
}
