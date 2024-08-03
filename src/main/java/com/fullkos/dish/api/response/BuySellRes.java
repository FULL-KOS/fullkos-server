package com.fullkos.dish.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fullkos.dish.api.dto.GetBuySellsDto;
import com.fullkos.dish.db.dto.BuySellDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BuySellRes extends BaseResponseBody {

    List<BuySellDto> buySells;
    @JsonProperty(value = "max_value")
    Long maxValue;

    static public BuySellRes of(int statusCode, String message, GetBuySellsDto dto) {

        BuySellRes buySellRes = BuySellRes.builder()
                .buySells(dto.getBuySells())
                .maxValue(dto.getMaxValue())
                .build();
        buySellRes.setStatusCode(statusCode);
        buySellRes.setMessage(message);

        return buySellRes;
    }
}
