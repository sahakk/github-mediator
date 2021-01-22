package com.github.mediator.api.contributor.converter;

import com.github.mediator.api.contributor.dto.ContributorAuthorDto;
import com.github.mediator.api.contributor.dto.ContributorDto;
import com.github.mediator.api.contributor.dto.WeekDto;
import com.github.mediator.domain.model.contributor.Contributor;
import com.github.mediator.domain.model.contributor.ContributorAuthor;
import com.github.mediator.domain.model.contributor.Week;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class ContributorConverter {

    public List<ContributorDto> convert(List<Contributor> contributors) {
        if (CollectionUtils.isEmpty(contributors)) {
            return new ArrayList<>();
        }
        return contributors.stream().map(this::convert).collect(Collectors.toList());
    }

    public ContributorDto convert(Contributor contributor) {
        return ContributorDto.builder()
            .total(contributor.getTotal())
            .weeksDto(convertWeeks(contributor.getWeeks()))
            .authorDto(convertAuthor(contributor.getAuthor()))
            .build();
    }

    private List<WeekDto> convertWeeks(final List<Week> weeks) {
        if (CollectionUtils.isEmpty(weeks)) {
            return null;
        }
        return weeks.stream()
            .map(this::convertWeek)
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    }

    private WeekDto convertWeek(final Week week) {
        if (week == null) {
            return null;
        }
        return WeekDto.builder()
            .a(week.getA())
            .c(week.getC())
            .d(week.getD())
            .w(week.getW())
            .build();
    }

    private ContributorAuthorDto convertAuthor(final ContributorAuthor author) {
        if (author == null) {
            return null;
        }
        return ContributorAuthorDto.builder()
            .login(author.getLogin())
            .build();
    }
}
