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
public class PortfolioDto {
    int company_id;
    String company_name;
    int amount;
    int company_code;
}
