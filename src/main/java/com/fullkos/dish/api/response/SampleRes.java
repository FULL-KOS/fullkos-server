package com.fullkos.dish.api.response;

import com.fullkos.dish.db.dto.SampleDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class SampleRes extends BaseResponseBody{
    String username;
    String companyName;
    String newsTitle;
    String Portfolio;
    String buyer;
    static public SampleRes of(int statusCode, String message, SampleDto dto) {
        SampleRes res = SampleRes.builder()
                .buyer(dto.getBuyer())
                .companyName(dto.getCompanyName())
                .newsTitle(dto.getNewsTitle())
                .Portfolio(dto.getPortfolio())
                .username(dto.getUsername())
                .build();
        res.setStatusCode(statusCode);
        res.setMessage(message);

        return res;
    }
}
