package com.fullkos.dish.api.dto.userReport;

import com.fullkos.dish.db.dto.PortfolioAndCompanyDto;
import com.fullkos.dish.db.dto.PortfolioAndCompanyDtoInterface;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;

@Getter
@Setter
public class UserReportResponseDto {
    String username;
    int allAmount;
    List<PortfolioDto> portfolio;
    List<IndustryDto> industryList;

    @Builder
    public UserReportResponseDto(String username, List<PortfolioAndCompanyDto> portfolioAndCompanyDtoList, List<IndustryDto> industryDtoList) {
        this.username = username;
        this.portfolio = new ArrayList<>();
        this.industryList = industryDtoList;
        for (PortfolioAndCompanyDto p : portfolioAndCompanyDtoList) {
            PortfolioDto portfolioDto = PortfolioDto.builder()
                                            .company_id(p.getCompanyId().intValue())
                                            .company_name(p.getCompanyName())
                                            .amount(p.getAmount())
                                            .company_code(p.getCompanyCode())
                                            .build();
            this.allAmount += p.getAmount();
            this.portfolio.add(portfolioDto);
        }

    }
}
