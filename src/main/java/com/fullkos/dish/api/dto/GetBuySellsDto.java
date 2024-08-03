package com.fullkos.dish.api.dto;

import com.fullkos.dish.db.dto.BuySellDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetBuySellsDto {
    List<BuySellDto> buySells;
    Long maxValue;
}
