package com.fullkos.dish.api.controller;
import com.fullkos.dish.api.Service.UserReportService;
import com.fullkos.dish.api.dto.userReport.UserReportRequestDto;
import com.fullkos.dish.api.dto.userReport.UserReportResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserReportController {
    private UserReportService userReportService;

    public UserReportController(UserReportService userReportService) {
        this.userReportService = userReportService;
    }
    @PostMapping("/api/userReport")
    public UserReportResponseDto makeUserReport(@RequestBody UserReportRequestDto userReportRequestDto) {
        UserReportResponseDto userReportResponseDto;
        userReportResponseDto = userReportService.makeUserReport(userReportRequestDto);
        return userReportResponseDto;
    }
}
