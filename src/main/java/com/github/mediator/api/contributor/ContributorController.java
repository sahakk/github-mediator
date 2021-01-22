package com.github.mediator.api.contributor;

import com.github.mediator.api.contributor.converter.ContributorConverter;
import com.github.mediator.api.contributor.dto.ContributorDto;
import com.github.mediator.domain.model.contributor.Contributor;
import com.github.mediator.domain.service.contributor.ContributorService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contributors")
public class ContributorController {

    private final ContributorConverter contributorConverter;
    private final ContributorService contributorService;

    public ContributorController(final ContributorService contributorService,
                                 final ContributorConverter contributorConverter) {
        this.contributorConverter = contributorConverter;
        this.contributorService = contributorService;
    }

    @RequestMapping(value = "/{owner}/{repo}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<ContributorDto>> getContributors(@PathVariable String owner, @PathVariable String repo) {
        final List<Contributor> contributors = contributorService.findContributors(owner, repo);
        return ResponseEntity.ok(contributorConverter.convert(contributors));
    }

}
