/*created by chenshi at   2019/4/15*/
package com.dytt.module.web.hystrix;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.utils.JsonUtil;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 线程池隔离--资源隔离
 */
public class GetMovieDetailCommand    extends HystrixCommand<String> {

    private Long    id;
    private RestTemplate    restTemplate;

    public GetMovieDetailCommand(Long    id,RestTemplate    restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("GetMovieDetailCommandGroup"));
//        super.super
        this.id=id;
        this.restTemplate=restTemplate;
    }

    @Override
    protected String run() throws Exception {
        ResponseEntity<JSONObject> result = restTemplate.getForEntity(
                "http://ZUUL/api-movie/selectById/" + id, JSONObject.class);
        return JsonUtil.toJSONString(result);
    }

}
