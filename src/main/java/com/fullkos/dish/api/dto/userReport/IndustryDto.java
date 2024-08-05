package com.fullkos.dish.api.dto.userReport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PACKAGE;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = PACKAGE)
public class IndustryDto {
    String industry;
    double pie;
}
