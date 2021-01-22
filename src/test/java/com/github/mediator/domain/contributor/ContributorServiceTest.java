package com.github.mediator.domain.contributor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.github.mediator.domain.model.contributor.Contributor;
import com.github.mediator.domain.service.contributor.ContributorServiceImpl;
import com.github.mediator.respository.ContributorRepositoryImpl;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ContributorServiceTest {

    @Mock
    private ContributorRepositoryImpl contributorRepository;

    @InjectMocks
    private ContributorServiceImpl contributorService;

    @Test
    void findContributors_whenOwnerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> contributorService
            .findContributors(null, "non_null_project"));
        verifyNoMoreInteractions(contributorRepository);
    }

    @Test
    void findContributors_whenProjectIsNull() {
        assertThrows(IllegalArgumentException.class,
            () -> contributorService.findContributors("non_null_owner", null));
        verifyNoMoreInteractions(contributorRepository);
    }

    @Test
    void findContributors_whenOwnerIsEmpty() {
        assertThrows(IllegalArgumentException.class,
            () -> contributorService.findContributors("", "non_null_project"));
        verifyNoMoreInteractions(contributorRepository);
    }

    @Test
    void findContributors_whenProjectIsEmpty() {
        assertThrows(IllegalArgumentException.class,
            () -> contributorService.findContributors("non_null_owner", ""));
        verifyNoMoreInteractions(contributorRepository);
    }

    @Test
    void findContributors_whenInputIsValid() {
        final String owner = "valid_owner";
        final String project = "valid_project";
        final List<Contributor> contributors = List
            .of(Contributor.builder().build());
        when(contributorRepository.findContributors(owner, project))
            .thenReturn(contributors);

        List<Contributor> result = contributorService
            .findContributors(owner, project);

        assertThat(result).containsExactlyInAnyOrderElementsOf(contributors);
        verifyNoMoreInteractions(contributorRepository);
    }
}
