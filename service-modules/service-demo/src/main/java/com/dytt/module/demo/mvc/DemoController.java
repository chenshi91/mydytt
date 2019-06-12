/* created by chenshi at 2019-02-02 */
package com.dytt.module.demo.mvc;

import com.dytt.common.mvc.BaseController;
import com.dytt.common.mvc.BaseService;
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
    public Boolean add(@RequestBody Demo demo) {
        return demoService.save(demo);
    }

    @GetMapping(value = {"/detail/{id}"})
    public Demo detail(@PathVariable Long id) {
        return demoService.getById(id);
    }


}
