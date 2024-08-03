package com.fullkos.dish.api.controller;

import com.fullkos.dish.api.Service.TradingService;
import com.fullkos.dish.api.dto.GetBuySellsDto;
import com.fullkos.dish.api.dto.GetVolumeDto;
import com.fullkos.dish.api.request.TradingReq;
import com.fullkos.dish.api.response.BaseResponseBody;
import com.fullkos.dish.api.response.BuySellRes;
import com.fullkos.dish.api.response.VolumeRes;
import com.fullkos.dish.db.dto.BuySellDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/trading")
public class TradingController {

    private final TradingService tradingService;

    public TradingController(TradingService tradingService) {
        this.tradingService = tradingService;
    }

    @GetMapping("/volume")
    public ResponseEntity<? extends BaseResponseBody> getVolume(@RequestBody TradingReq tradingReq) {

        VolumeRes res;
        try
        {
            GetVolumeDto dto = tradingService.getVolume(tradingReq.getIndustry());
            res = VolumeRes.of(200, "success", dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping("/buysell")
    public ResponseEntity<? extends BaseResponseBody> getBuySell(@RequestBody TradingReq tradingReq) {
        BuySellRes res;
        try
        {
            GetBuySellsDto dto = tradingService.getBuySell(tradingReq.getIndustry());
            res = BuySellRes.of(200, "success", dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
