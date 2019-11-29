/* created by chenshi at 2019-02-02 */
package com.dytt.module.test.mvc;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dytt.common.mvc.BaseController;
import com.dytt.common.mvc.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;


@RestController
public class DemoController extends BaseController<Demo> {

    //    private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);
    @Autowired
    DemoService demoService;
    @Autowired
    RedisTemplate redisTemplate;
//    @Autowired
//    CacheManager cacheManager;
//    @Autowired
//    MovieRemoteService movieService;


    @PostMapping(value = {"/add"})
    public ResponseResult add(@Validated @RequestBody DemoForm demoForm) {
//        if (bindingResult.hasErrors()) {
//            return new ResponseResult("112233",
//                    bindingResult.getAllErrors().get(0).getDefaultMessage());
//        }
//        HttpServletRequest request = WebUtils.getRequest();
        Demo demo = new Demo();
        BeanUtil.copyProperties(demoForm,demo);
        boolean insert = demoService.save(demo);
        return new ResponseResult(insert ? "ok" : "fail");
    }

    //    @Cacheable(cacheNames = "stream", key = "#id")
//    @AddRedisCash
    @GetMapping(value = {"/selectById/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult selectById(@PathVariable Long id) {
        Demo demo = demoService.getById(id);
        return new ResponseResult(demo);
    }

    @CacheEvict(cacheNames = {"demo", "demoList"}, key = "#id", allEntries = true)
    @GetMapping(value = {"/delete/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult delete2(@PathVariable Long id) {
        boolean delete = demoService.removeById(id);
        return new ResponseResult(delete ? "ok" : "fail");
    }

    //    @AddRedisCash
    @GetMapping(value = {"/movie/selectById/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public JSONObject detail2(@PathVariable int id, @RequestParam String name, @RequestParam String home, HttpServletRequest request) {
        String accept = request.getHeader("Accept");
//        JSONObject jsonObject = movieService.selectById(id);
        return null;
    }

    @GetMapping(value = {"/exceptionTest"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult exceptionTest() {
//        logger.info("--------------------------2222222222-----------------");
//        LogUtil
        HashMap[] map = new HashMap[5];
        map[5].put("1", "2");

        return new ResponseResult("cdhhgh");
    }

    @GetMapping(value = {"/selectPage/{pageNo}/{pageSize}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult selectPage(@PathVariable int pageNo,
                                     @PathVariable int pageSize,
                                     @RequestParam String name) {
        List<Demo> list = demoService.page(
                new Page<Demo>(pageNo, pageSize),
                new QueryWrapper<Demo>().eq("name", name)
        ).getRecords();
        return new ResponseResult(list);
    }

}
