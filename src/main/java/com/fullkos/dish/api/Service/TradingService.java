package com.fullkos.dish.api.Service;

import com.fullkos.dish.api.dto.GetVolumeDto;

public interface TradingService {
    public GetVolumeDto getVolume(String industry);
}
