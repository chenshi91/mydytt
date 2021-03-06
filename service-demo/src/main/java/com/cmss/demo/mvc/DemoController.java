/* created by chenshi at 2019-02-02 */
package com.cmss.demo.mvc;

import com.alibaba.fastjson.JSONObject;
import com.cmss.dytt.common.web.mvc.BaseController;
import com.cmss.dytt.common.web.mvc.BaseService;
import com.cmss.dytt.common.web.mvc.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController extends BaseController<Demo> {
    @Autowired
    DemoService demoService;

    @Override
    protected BaseService<Demo> getService() {
        return demoService;
    }

    @PostMapping(value = {"/add"})
    public ResponseResult add(@RequestBody Demo demo) {
        return super.insertSelective(demo);
    }

    @GetMapping(value = {"/detail/{id}"})
    public ResponseResult detail(@PathVariable Long id) {
        return super.detail(id);
    }


}
