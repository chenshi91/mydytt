package com.dytt.module.user.feign;

import com.dytt.common.mvc.ResponseResult;
import com.dytt.module.user.fallback.UserApiFeignFallback;
import com.dytt.module.user.form.UserForm;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-module-user", fallback = UserApiFeignFallback.class)
public interface UserApiFeign {

    @ApiOperation(value = "用户分页查询")
    @GetMapping(value = {"/selectWithPage"})
    ResponseResult selectWithPage(@RequestParam("pageNo") Integer pageNo,
                                  @RequestParam("pageSize") Integer pageSize,
                                  @RequestParam(value = "nickName", required = false) String nickName);

    @ApiOperation(value = "用户详情查询")
    @GetMapping(value = {"/getById"})
    ResponseResult getById(@RequestParam(value = "id") Long id);

    @ApiOperation(value = "用户添加")
    @PostMapping(value = {"/insert"})
    ResponseResult insert(@RequestBody @Validated UserForm userForm);

    @ApiOperation(value = "用户修改")
    @PostMapping(value = {"/insert"})
    ResponseResult update(@RequestBody @Validated UserForm userForm);

    @ApiOperation(value = "用户删除")
    @PostMapping(value = "/delete")
    ResponseResult delete(@RequestParam("id") Long id);
}
