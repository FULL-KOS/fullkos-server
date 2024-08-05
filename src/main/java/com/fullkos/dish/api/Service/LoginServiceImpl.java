package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.login.LoginRequestDto;
import com.fullkos.dish.api.dto.login.LoginResponseDto;
import com.fullkos.dish.db.entity.User;
import com.fullkos.dish.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{
    private UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());
        LoginResponseDto loginResponseDto;
        if (user.isEmpty()) {
            loginResponseDto = LoginResponseDto.builder().message("LOGIN FAIL").build();
        }
        else {
            if (user.get().getPassword().equals(loginRequestDto.getPassword())) {
                loginResponseDto = LoginResponseDto.builder().message("LOGIN SUCCESS").build();
            }
            else {
                loginResponseDto = LoginResponseDto.builder().message("LOGIN FAIL").build();
            }
        }

        return loginResponseDto;
    }
}
