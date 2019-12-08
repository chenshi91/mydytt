/* created by chenshi at 2019-02-02 */
package com.dytt.module.demo.mvc;

import com.dytt.common.mvc.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DemoController extends BaseController{
    @Autowired
    DemoService demoService;


    @PostMapping(value = {"/add"})
    public Boolean add(@RequestBody Demo demo) {
        return demoService.save(demo);
    }

    @GetMapping(value = {"/detail/{id}"})
    public Demo detail(@PathVariable Long id) {
        return demoService.getById(id);
    }


}
