package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.userReport.IndustryDto;
import com.fullkos.dish.api.dto.userReport.UserReportRequestDto;
import com.fullkos.dish.api.dto.userReport.UserReportResponseDto;
import com.fullkos.dish.db.dto.PortfolioAndCompanyDto;

import java.util.List;

public interface UserReportService {
    UserReportResponseDto makeUserReport(UserReportRequestDto userReportRequestDto);
    List<IndustryDto> makeIndustryReport(List<PortfolioAndCompanyDto> portfolioAndCompanyDtoList);
}
