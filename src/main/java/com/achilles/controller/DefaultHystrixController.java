package com.achilles.controller;

import com.achilles.model.response.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DefaultHystrixController {
    @RequestMapping("/fallback")
    public DataResult<Object> fallback(){

        //log.error("触发熔断......");
        return DataResult.baseFail();
    }
}