package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.login.LoginRequestDto;
import com.fullkos.dish.api.dto.login.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
