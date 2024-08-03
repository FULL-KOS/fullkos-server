package com.fullkos.dish.db.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VolumeDto {
    @JsonProperty(value = "company_id")
    Long companyId;
    @JsonProperty(value = "company_name")
    String companyName;
    Long total;
}
