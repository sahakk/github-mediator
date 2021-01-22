package com.github.mediator.domain.model.contributor;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Builder
@Data
public class Contributor {
    private int total;
    private List<Week> weeks;
    private ContributorAuthor author;
}
