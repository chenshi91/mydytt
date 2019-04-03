/* created by chenshi at 2019-02-26 */
package com.dytt.web.service;

import com.dytt.common.model.mvc.ResponseResult;
import org.springframework.stereotype.Component;

@Component
public class MovieServiceHystric implements MovieService {

    private ResponseResult getFailResult() {
        return new ResponseResult("94512", "lianjieshibai");
    }


    @Override
    public ResponseResult listWithPage(int pageNo, int pageSize) {
        return getFailResult();
    }

    @Override
    public ResponseResult selectById(Long id) {
        return getFailResult();
    }
}
