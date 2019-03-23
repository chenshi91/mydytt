/* created by chenshi at 2019-02-02 */
package com.cmss.test.mvc;

import com.dytt.common.model.mvc.BaseController;
import com.dytt.common.model.mvc.BaseService;
import com.dytt.common.model.mvc.ResponseResult;
import com.dytt.common.model.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
public class DemoController extends BaseController<Demo> {

//    private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);
    @Autowired
    DemoService demoService;

    @Override
    protected BaseService<Demo> getService() {
        return demoService;
    }

    @PostMapping(value = {"/add"})
    public ResponseResult add(@RequestBody Demo demo) {
        HttpServletRequest request = WebUtils.getRequest();
        return super.insertSelective(demo);
    }

    @GetMapping(value = {"/detail/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult detail(@PathVariable Long id) {
        return super.detail(id);
    }

    @GetMapping(value = {"/detail2/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public Demo detail2(@PathVariable Long id, HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        Demo demo = demoService.selectByPrimaryKey2(id);
        return demo;
    }

    @GetMapping(value = {"/exceptionTest"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult exceptionTest() {
//        logger.info("--------------------------2222222222-----------------");
//        LogUtil
        HashMap[] map=new HashMap[5];
        map[5].put("1","2");

        return new ResponseResult("cdhhgh");
    }


}
