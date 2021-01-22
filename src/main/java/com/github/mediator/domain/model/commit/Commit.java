package com.github.mediator.domain.model.commit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
   private String sha;
   private String message;
   private String author;
   private String commitDate;
}
