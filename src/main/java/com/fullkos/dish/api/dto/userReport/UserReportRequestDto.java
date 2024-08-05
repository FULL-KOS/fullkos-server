package com.fullkos.dish.api.dto.userReport;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import static lombok.AccessLevel.PACKAGE;

@Getter
@Builder
public class UserReportRequestDto {
    String email;
    String dummy;
}
