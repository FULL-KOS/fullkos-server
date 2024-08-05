package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.userReport.IndustryDto;
import com.fullkos.dish.api.dto.userReport.UserReportRequestDto;
import com.fullkos.dish.api.dto.userReport.UserReportResponseDto;
import com.fullkos.dish.db.dto.PortfolioAndCompanyDto;
import com.fullkos.dish.db.dto.PortfolioAndCompanyDtoInterface;
import com.fullkos.dish.db.dto.VolumeDto;
import com.fullkos.dish.db.entity.Portfolio;
import com.fullkos.dish.db.entity.User;
import com.fullkos.dish.db.repository.CompanyRepository;
import com.fullkos.dish.db.repository.PortfolioRepository;
import com.fullkos.dish.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserReportServiceImpl implements UserReportService {
    private UserRepository userRepository;
    private PortfolioRepository portfolioRepository;
    private CompanyRepository companyRepository;

    public UserReportServiceImpl(UserRepository userRepository, PortfolioRepository portfolioRepository,
                                 CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.portfolioRepository = portfolioRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserReportResponseDto makeUserReport(UserReportRequestDto userReportRequestDto) {
        Optional<User> user = userRepository.findByEmail(userReportRequestDto.getEmail());
        List<PortfolioAndCompanyDtoInterface> portfolioAndCompanyDtoInterfaces = portfolioRepository.findAllByUserId(user.get().getId());
        List<PortfolioAndCompanyDto> portfolioAndCompanyDtoList = portfolioAndCompanyDtoInterfaces.stream().map(
                                                                    p -> PortfolioAndCompanyDto.builder()
                                                                            .companyId(p.getCompanyId())
                                                                            .companyName(p.getCompanyName())
                                                                            .amount(p.getAmount().intValue())
                                                                            .companyCode(p.getCompanyCode())
                                                                            .industry(p.getIndustry())
                                                                            .build()
                                                            ).collect(Collectors.toList());

        List<IndustryDto> industryDtoList = makeIndustryReport(portfolioAndCompanyDtoList);

        UserReportResponseDto userReportResponseDto = UserReportResponseDto.builder()
                                                        .username(user.get().getUsername())
                                                        .portfolioAndCompanyDtoList(portfolioAndCompanyDtoList)
                                                        .industryDtoList(industryDtoList)
                                                        .build();

        return userReportResponseDto;
    }

    @Override
    public List<IndustryDto> makeIndustryReport(List<PortfolioAndCompanyDto> portfolioAndCompanyDtoList) {
        Map<String, Integer> industryOrder = new HashMap<>();
        int sum = 0;

        for (PortfolioAndCompanyDto p : portfolioAndCompanyDtoList) {
            industryOrder.put(p.getIndustry(), industryOrder.getOrDefault(p.getIndustry(), 0) + p.getAmount());
            sum += p.getAmount();
        }
        List<String> keySet = new ArrayList<>(industryOrder.keySet());

        // Value 값으로 오름차순 정렬
        keySet.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return industryOrder.get(o1).compareTo(industryOrder.get(o2));
            }
        });

        keySet.sort((o1, o2) -> industryOrder.get(o2).compareTo(industryOrder.get(o1)));

        List<IndustryDto> industryDtoList = new ArrayList<>();
        for (String k : keySet) {
            double tmp = industryOrder.get(k);
            IndustryDto industryDto = IndustryDto.builder().industry(k).pie((tmp/sum)*100).build();
            industryDtoList.add(industryDto);
        }

        return industryDtoList.subList(0, 3);
    }

}
