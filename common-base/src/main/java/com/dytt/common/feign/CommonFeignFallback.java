package com.dytt.common.feign;

import com.dytt.common.constance.ResponseResultConstance;
import com.dytt.common.mvc.ResponseResult;

public class CommonFeignFallback {

    public ResponseResult   feignFailback(String    s){
        return new ResponseResult(ResponseResultConstance.FEIGN_FAIL_CODE, ResponseResultConstance.FEIGN_FAIL_MSG, s);
    }
}
