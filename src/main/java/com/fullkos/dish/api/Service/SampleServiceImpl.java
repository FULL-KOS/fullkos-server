package com.fullkos.dish.api.Service;

import com.fullkos.dish.db.dto.SampleDto;
import com.fullkos.dish.db.repository.CompanyRepository;
import com.fullkos.dish.db.repository.NewsRepository;
import com.fullkos.dish.db.repository.PortfolioRepository;
import com.fullkos.dish.db.repository.TradingRepository;
import com.fullkos.dish.db.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService{

    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    private NewsRepository newsRepository;
    private PortfolioRepository portfolioRepository;
    private TradingRepository tradingRepository;

    public SampleServiceImpl(UserRepository userRepository, CompanyRepository companyRepository,
                             NewsRepository newsRepository, PortfolioRepository portfolioRepository, TradingRepository tradingRepository){
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.newsRepository = newsRepository;
        this.portfolioRepository = portfolioRepository;
        this.tradingRepository = tradingRepository;
    }

    @Override
    public SampleDto getSampleInfo(Long id) {

        SampleDto sampleDto = SampleDto.builder()
                .username(userRepository.findById(id).get().getUsername())
                .buyer(tradingRepository.findById(id).get().getBuyer())
                .companyName(companyRepository.findById(id).get().getCompanyName())
                .newsTitle(newsRepository.findById(id).get().getTitle())
                .Portfolio(portfolioRepository.findById(id).get().getCompany().getCompanyCode() + " " + portfolioRepository.findById(id).get().getAmount())
                .build();

        return sampleDto;
    }
}
