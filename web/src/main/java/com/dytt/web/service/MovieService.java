package com.dytt.web.service;

import com.dytt.common.model.mvc.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "zuul", fallback = MovieServiceHystric.class)
public interface MovieService {

    @GetMapping(value = "/api-movie/list/{pageNo}/{pageSize}", produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult listWithPage(@PathVariable(value = "pageNo") int pageNo,
                                       @PathVariable(value = "pageSize") int pageSize);

    @GetMapping(value = {"/api-movie/selectById/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult selectById(@PathVariable(value = "id") Long id);


}
