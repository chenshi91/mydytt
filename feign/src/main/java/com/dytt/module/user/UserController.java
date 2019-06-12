/* created by chenshi at 2018-10-25 */
package com.dytt.module.user;


import com.alibaba.fastjson.JSONObject;
import com.dytt.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/selectById/{id}"})
    public JSONObject hi(@PathVariable(value = "id") Long id) {
        return userService.hi(id);
    }
}