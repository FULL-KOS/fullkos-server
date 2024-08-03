package com.fullkos.dish.api.controller;

import com.fullkos.dish.api.Service.SampleService;
import com.fullkos.dish.api.response.BaseResponseBody;
import com.fullkos.dish.api.response.SampleRes;
import com.fullkos.dish.db.dto.SampleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class SampleController {

    private SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends BaseResponseBody> home(@PathVariable("id") Long id) {

        SampleDto dto = sampleService.getSampleInfo(id);
        SampleRes res = SampleRes.of(200, "✨조아요", dto);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
