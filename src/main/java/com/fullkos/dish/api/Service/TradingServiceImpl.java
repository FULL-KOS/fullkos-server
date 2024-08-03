package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.GetBuySellsDto;
import com.fullkos.dish.api.dto.GetVolumeDto;
import com.fullkos.dish.db.dto.BuySellDto;
import com.fullkos.dish.db.dto.BuySellDtoInterface;
import com.fullkos.dish.db.dto.VolumeDto;
import com.fullkos.dish.db.dto.VolumeDtoInterface;
import com.fullkos.dish.db.repository.CompanyRepository;
import com.fullkos.dish.db.repository.TradingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;

@Service
public class TradingServiceImpl implements TradingService{

    private final CompanyRepository companyRepository;
    private final TradingRepository tradingRepository;

    public TradingServiceImpl(CompanyRepository companyRepository, TradingRepository tradingRepository){
        this.companyRepository = companyRepository;
        this.tradingRepository = tradingRepository;
    }

    @Override
    public GetVolumeDto getVolume(String industry) {
        List<VolumeDtoInterface> volumeDtoInterfaces = tradingRepository.findTop10VolumeByIndustry(industry);
        if(volumeDtoInterfaces.size() == 0) {
            throw new IllegalStateException("No results");
        }
        List<VolumeDto> volumes = volumeDtoInterfaces.stream().map(
            v -> VolumeDto.builder()
                    .companyId(v.getCompanyId())
                    .companyName(v.getCompanyName())
                    .total(v.getTotal())
                    .build()
        ).collect(Collectors.toList());
        Long overall = volumes.stream().map(VolumeDto::getTotal).collect(reducing(Long::sum)).get();
        GetVolumeDto getVolumeDto = GetVolumeDto.builder()
                .volumes(volumes)
                .overall(overall)
                .build();

        return getVolumeDto;
    }

    @Override
    public GetBuySellsDto getBuySell(String industry) {
        List<BuySellDtoInterface> buySellDtoInterfaces = tradingRepository.findTop10BuySellByIndustry(industry);
        if(buySellDtoInterfaces.size() == 0) {
            throw new IllegalStateException("No results");
        }
        List<BuySellDto> buySells = buySellDtoInterfaces.stream().map(
                v -> BuySellDto.builder()
                        .companyId(v.getCompanyId())
                        .companyName(v.getCompanyName())
                        .buyTotal(v.getBuyTotal())
                        .sellTotal(v.getSellTotal())
                        .build()
        ).collect(Collectors.toList());
        Long maxValue = 0L;
        for(BuySellDto v : buySells) {
            maxValue = Math.max(maxValue, Math.max(Math.abs(v.getBuyTotal()), Math.abs(v.getSellTotal())));
        }
        GetBuySellsDto buySellsDto = GetBuySellsDto.builder()
                .buySells(buySells)
                .maxValue(maxValue)
                .build();

        return buySellsDto;
    }
}
