/* created by chenshi at 2018-10-24 */
package com.dytt.module.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dytt.common.mvc.BaseController;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.common.utils.StringUtil;
import com.dytt.module.user.form.UserForm;
import com.dytt.module.user.model.User;
import com.dytt.module.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author:chenshi
 */
@Api(description = "用户操作接口")
@Slf4j
@RefreshScope
@RestController
public class UserController extends BaseController {
    @Autowired
    UserService userService;
//    @Autowired
//    EsRemoteService esRemoteService;
//    @Autowired
//    MovieRemoteService movieRemoteService;

    @ApiOperation(value = "用户分页查询")
    @GetMapping(value = {"/selectWithPage"})
    public ResponseResult selectWithPage(@RequestParam("pageNo") Integer pageNo,
                                         @RequestParam("pageSize") Integer pageSize,
                                         @RequestParam(value = "nickName", required = false) String nickName) {
        IPage<User> userPage = userService.page(
                new Page<>(pageNo, pageSize),
                new QueryWrapper<User>()
                        .like(!StringUtil.isNull(nickName), "nick_name", nickName)
                        .orderByAsc("create_date")
        );
        return new ResponseResult(userPage);
    }


    @ApiOperation(value = "用户详情查询")
    @GetMapping(value = {"/getById"})
    public ResponseResult getById(@RequestParam(value = "id") Long id) {
        User user = userService.getById(id);
        return new ResponseResult(user);
    }

    @ApiOperation(value = "用户添加")
    @PostMapping(value = {"/insert"})
    public ResponseResult insert(@RequestBody @Validated UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        user.setCreateDate(new Date());
        user.setLastUpdateDate(new Date());
        boolean saveOrUpdate = userService.save(user);
//        esRemoteService.addUser(JsonUtil.parseJsonObject(user));
        log.info("-------------------数据添加成功!------");
        return new ResponseResult(saveOrUpdate);
    }

    @ApiOperation(value = "用户修改")
    @PostMapping(value = {"/update"})
    public ResponseResult update(@RequestBody @Validated UserForm userForm) {
        User user = new User();
        BeanUtils.copyProperties(userForm, user);
        user.setLastUpdateDate(new Date());
        boolean update = userService.updateById(user);
//        esRemoteService.addUser(JsonUtil.parseJsonObject(user));
        return new ResponseResult(update);
    }

    @ApiOperation(value = "用户删除")
    @PostMapping(value = "/delete")
    public ResponseResult delete(@RequestParam("id") Long id) {
        return new ResponseResult(userService.removeById(id));
    }


}
