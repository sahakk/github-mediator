package com.github.mediator.api.contributor;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.mediator.api.contributor.converter.ContributorConverter;
import com.github.mediator.api.contributor.dto.ContributorAuthorDto;
import com.github.mediator.api.contributor.dto.ContributorDto;
import com.github.mediator.api.contributor.dto.WeekDto;
import com.github.mediator.domain.model.contributor.Contributor;
import com.github.mediator.domain.model.contributor.ContributorAuthor;
import com.github.mediator.domain.model.contributor.Week;
import com.github.mediator.domain.service.contributor.ContributorService;
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
@WebMvcTest(controllers = ContributorController.class)
public class ContributorControllerTest {

    private static final String ROOT_PATH = "/contributors";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContributorConverter contributorConverter;

    @MockBean
    private ContributorService contributorService;

    final String owner = "owner1";
    final String project = "project1";
    final String authorLogin = "simple_login";
    final int total = 1;

    @Test
    void givenPaginationRequest_whenGettingAll_thenOk() throws Exception {
        final List<Contributor> contributors = createContributors(1);
        when(contributorService.findContributors(owner, project))
            .thenReturn(contributors);
        when(contributorConverter.convert(isA(List.class)))
            .thenReturn(List.of(createContributorDto()));

        mockMvc.perform(get(ROOT_PATH + "/owner1/project1"))
            .andExpect(status().isOk());

        verify(contributorService).findContributors(owner, project);
        verify(contributorConverter).convert(isA(List.class));
        verifyAllNoMoreInteractions();
    }

    private void verifyAllNoMoreInteractions() {
        verifyNoMoreInteractions(contributorService);
        verifyNoMoreInteractions(contributorConverter);
    }

    private List<Contributor> createContributors(int size) {
        return IntStream.range(0, size).mapToObj(it -> createContributor())
            .collect(Collectors.toList());
    }

    private ContributorDto createContributorDto() {
        ContributorDto dto = new ContributorDto();
        dto.setTotal(total);
        dto.setAuthorDto(createAuthorDto());
        dto.setWeeksDto(List.of(new WeekDto(1, 2, 3, 4)));
        return dto;
    }

    private Contributor createContributor() {
        return Contributor.builder()
            .total(total)
            .weeks(createWeeks())
            .author(createAuthor()).build();
    }

    private List<Week> createWeeks() {
        final Week week = new Week();
        week.setA(1);
        week.setC(2);
        week.setD(3);
        week.setW(4);
        return List.of(week);
    }

    private ContributorAuthor createAuthor() {
        final ContributorAuthor author = new ContributorAuthor();
        author.setId(1);
        author.setLogin(authorLogin);
        return author;
    }

    private ContributorAuthorDto createAuthorDto() {
        final ContributorAuthorDto author = new ContributorAuthorDto();
        author.setLogin(authorLogin);
        return author;
    }
}
