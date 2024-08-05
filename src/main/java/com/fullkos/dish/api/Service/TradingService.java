package com.fullkos.dish.api.Service;

import java.util.List;

import com.fullkos.dish.api.dto.GetBuySellsDto;
import com.fullkos.dish.api.dto.MonthlyCompanyTradingDto;
import com.fullkos.dish.api.dto.GetVolumeDto;

public interface TradingService {
    GetVolumeDto getVolume(String industry);
    GetBuySellsDto getBuySell(String industry);
    List<MonthlyCompanyTradingDto> getMonthlyCompanyTradingDto(Long companyId);
}
