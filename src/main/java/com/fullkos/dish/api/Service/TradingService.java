package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.GetBuySellsDto;
import com.fullkos.dish.api.dto.GetVolumeDto;

public interface TradingService {
    GetVolumeDto getVolume(String industry);
    GetBuySellsDto getBuySell(String industry);
}
