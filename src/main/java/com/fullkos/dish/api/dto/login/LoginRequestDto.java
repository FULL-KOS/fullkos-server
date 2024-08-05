package com.fullkos.dish.api.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PACKAGE;

@Getter
@Builder
public class LoginRequestDto {
    String email;
    String password;
}
