package com.dytt.web.controller;

import com.dytt.common.mvc.ResponseResult;
import com.dytt.web.api.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//    @Autowired
//    UserApi userApi;
//
//    @GetMapping(value = "/user/getByid")
//    public String getByid(@RequestParam(value = "id") Long  id) {
//        ResponseResult result = userApi.getById(id);
//        return "demo";
//    }
}
