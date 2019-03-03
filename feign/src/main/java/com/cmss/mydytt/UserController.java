/* created by chenshi at 2018-10-25 */
package com.cmss.mydytt;


import com.alibaba.fastjson.JSONObject;
import com.cmss.mydytt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"/hi2"})
    public JSONObject hi() {
        return userService.hi();
    }
}
