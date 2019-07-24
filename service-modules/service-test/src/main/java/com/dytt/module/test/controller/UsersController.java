package com.dytt.module.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dytt.common.mvc.BaseController;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.common.threadPool.ThreadPoolManager;
import com.dytt.common.utils.RedisLockUtil;
import com.dytt.common.utils.StringUtil;
import com.dytt.module.test.entity.Users;
import com.dytt.module.test.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author chenshi
 * @since 2019-06-20
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UsersController extends BaseController {

    @Autowired
    UsersService    usersService;

    @GetMapping("/aa")
    public IPage<Users> aa(@RequestParam String   nickName){
        IPage<Users> usersIPage = usersService.page(
                new Page<Users>(1, 10),
                new QueryWrapper<Users>().select("id,phone,nick_name")
                .like(!StringUtil.isEmpty(nickName),"nick_name",nickName)
                .orderByAsc("id")
        );

        Map<String, Object> map = usersService.getMap(new QueryWrapper<Users>()
                .select("id,nick_name")
                .like("nick_name", nickName)
        );


//        usersService.get

        return usersIPage;
    }

    @GetMapping(value = {"/addRedisLock"})
    public ResponseResult addRedisLock(){
        for (int i = 0; i < 10; i++) {
            ThreadPoolManager.getInstance().execute(()->{
                new RedisLockUtil().doServiceWithRedislock("redis_key","redis_value",()->{
                    log.info("------do-services---");
                });
            });
        }
        return new ResponseResult(null);
    }

}

