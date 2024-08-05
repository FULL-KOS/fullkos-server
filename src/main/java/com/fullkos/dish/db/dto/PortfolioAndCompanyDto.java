package com.fullkos.dish.db.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PortfolioAndCompanyDto {
    @JsonProperty(value = "company_id")
    Long companyId;
    @JsonProperty(value = "company_name")
    String companyName;
    @JsonProperty(value = "amount")
    int amount;
    @JsonProperty(value = "company_code")
    int companyCode;
    @JsonProperty(value = "industry")
    String industry;
}
