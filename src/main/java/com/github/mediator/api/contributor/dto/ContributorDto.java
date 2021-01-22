package com.github.mediator.api.contributor.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContributorDto {

    private int total;
    private ContributorAuthorDto authorDto;
    private List<WeekDto> weeksDto;
}
