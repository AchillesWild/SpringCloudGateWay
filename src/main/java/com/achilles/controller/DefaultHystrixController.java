//package com.achilles.controller;
//
//import com.achilles.model.response.DataResult;
//import com.achilles.model.response.code.BaseResultCode;
//
//@RestController
//@Slf4j
//public class DefaultHystrixController {
//    @RequestMapping("/fallback")
//    public DataResult<Object> fallback(){
//
//        //log.error("触发熔断......");
//        return DataResult.baseFail(BaseResultCode.FAIL.code,"GateWay fallback ***********");
//    }
//}