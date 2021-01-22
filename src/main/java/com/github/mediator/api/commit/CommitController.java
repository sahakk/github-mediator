package com.github.mediator.api.commit;

import com.github.mediator.api.commit.converter.CommitConverter;
import com.github.mediator.api.commit.dto.CommitDto;
import com.github.mediator.domain.model.commit.Commit;
import com.github.mediator.domain.service.commit.CommitService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/commits")
public class CommitController {

    private final CommitService commitService;
    private final CommitConverter commitConverter;

    @RequestMapping(value = "/{owner}/{repo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<CommitDto>> getCommits(
        @PathVariable String owner, @PathVariable String repo) {
        final List<Commit> commits = commitService.findCommits(owner, repo);
        return ResponseEntity.ok(commitConverter.convert(commits));
    }
}
