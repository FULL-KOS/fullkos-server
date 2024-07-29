package com.fullkos.dish.api.controller;

import com.fullkos.dish.api.response.BaseResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class HomeController {

    @GetMapping("")
    public ResponseEntity<? extends BaseResponseBody> home() {
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponseBody.of(200, "test"));
    }
}
