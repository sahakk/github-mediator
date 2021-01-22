package com.github.mediator.api.commit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommitDto {
  private String commitDate;
  private String sha;
  private String author;
  private String message;
}
