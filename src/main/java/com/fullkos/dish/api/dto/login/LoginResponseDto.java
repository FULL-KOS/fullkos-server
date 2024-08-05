package com.fullkos.dish.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static lombok.AccessLevel.PACKAGE;

@Getter
@Setter
@Builder
public class LoginResponseDto {
    String message;
}
