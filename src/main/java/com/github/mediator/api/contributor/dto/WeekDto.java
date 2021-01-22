package com.github.mediator.api.contributor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeekDto {
    private long w;
    private long a;
    private long d;
    private long c;
}
