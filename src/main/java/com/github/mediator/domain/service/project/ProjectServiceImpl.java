package com.github.mediator.domain.service.project;

import com.github.mediator.domain.model.project.Project;
import com.github.mediator.respository.ProjectRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.util.Assert.hasText;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepositoryImpl repository;

    public List<Project> findProjects(final String term) {
        assertTerm(term);
        return repository.findProjects(term);
    }

    private void assertTerm(final String term) {
        hasText(term, "Term should not be blank");
    }
}
