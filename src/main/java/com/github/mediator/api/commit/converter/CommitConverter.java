package com.github.mediator.api.commit.converter;

import com.github.mediator.api.commit.dto.CommitDto;
import com.github.mediator.domain.model.commit.Commit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@RequiredArgsConstructor
public class CommitConverter {

    public List<CommitDto> convert(final List<Commit> commits) {
        if(CollectionUtils.isEmpty(commits)) {
            return new ArrayList<>();
        }
        return commits.stream().map(this::convert).collect(Collectors.toList());
    }

    public CommitDto convert(final Commit commit) {
        return CommitDto.builder()
            .author(commit.getAuthor())
            .commitDate(commit.getCommitDate())
            .message(commit.getMessage())
            .sha(commit.getSha())
            .build();
    }
}
