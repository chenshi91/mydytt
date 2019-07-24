/* created by chenshi at 2018-10-24 */
package com.dytt.module.user.mvc;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dytt.bridge.services.EsRemoteService;
import com.dytt.bridge.services.MovieRemoteService;
import com.dytt.common.mvc.BaseController;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.common.utils.JsonUtil;
import com.dytt.common.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author:chenshi
 */
@Slf4j
@RefreshScope
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    EsRemoteService esRemoteService;
    @Autowired
    MovieRemoteService movieRemoteService;

    @GetMapping(value = {"/page"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult pageWithCondition(@RequestParam("pageNo") Integer pageNo,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam(value = "nickName",required = false) String nickName) {
        IPage<User> userPage = userService.page(
                new Page<>(pageNo,pageSize),
                new QueryWrapper<User>()
                        .like(!StringUtil.isNull(nickName), "nick_name", nickName)
                        .orderByAsc("create_date")
        );
        return new ResponseResult(userPage);
    }

    @PostMapping(value = {"/insert"})
    public ResponseResult insert(@RequestBody @Validated User user) {
        user.setCreateDate(new Date());
        user.setCreateUserId("1");
        user.setLastUpdateUserId("1");
        user.setLastUpdateDate(new Date());
        boolean saveOrUpdate = userService.saveOrUpdate(user);
        esRemoteService.addUser(JsonUtil.parseJsonObject(user));
        log.info("-------------------数据添加成功!------");
        return new ResponseResult(saveOrUpdate);
    }

    @GetMapping(value = {"/hi"})
    public String hi() {
        return "hi,i back home ! hn32297";
    }

//    @Value("${server.port}")
//    String port;
//    @Value("${name}")
//    String name;
//    @Value("${mongodbname}")
//    String mongodbname;
//    @Value("${spring.rabbitmq.port}")
//    String rabbitMQ_ip;

//    @GetMapping(value = {"/hi"})
//    public JSONObject hi() {
//        JSONObject responseResult = new JSONObject();
//        responseResult.put("code", "200");
//        responseResult.put("msg", "ok");
//        responseResult.put("rabbitMQ_ip", rabbitMQ_ip);
//        responseResult.put("port", port);
//        responseResult.put("mongodbname", mongodbname);
//        responseResult.put("name", name);
//        return responseResult;
//    }



    @GetMapping(value = {"/movie/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public JSONObject page(@PathVariable("id") int id){
        JSONObject responseResult = movieRemoteService.selectById(id);
        return responseResult;
    }
}
