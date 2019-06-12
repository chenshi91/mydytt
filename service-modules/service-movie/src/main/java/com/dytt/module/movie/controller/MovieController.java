/* created by chenshi at 2018-11-10 */
package com.dytt.module.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dytt.common.mvc.BaseController;
import com.dytt.common.mvc.BaseService;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.module.movie.entity.Movie;
import com.dytt.module.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

@RestController
public class MovieController extends BaseController<Movie> {

    @Override
    protected BaseService<Movie> getService() {
        return movieService;
    }


    @Autowired
    MovieService movieService;
//    @Autowired
//    DemoRemoteService demoService;


    @GetMapping(value = "/movie/test1")
    public JSONObject test1(HttpServletRequest httpServletRequest) throws InterruptedException {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String requestURI = request.getRequestURI();
        Enumeration<String> attributeNames = request.getAttributeNames();

        System.out.println("TEST1--------------------------");
        Thread.sleep(1000);
        JSONObject result = new JSONObject();
        result.put("code", "000000");
        result.put("msg", "test1");
        return result;
    }

    @GetMapping(value = "test2")
    public JSONObject test2() throws InterruptedException {
        System.out.println("TEST2--------------------------");
        Thread.sleep(2000);
        JSONObject result = new JSONObject();
        result.put("code", "000000");
        result.put("msg", "test2");
        return result;
    }

    @GetMapping(value = "test3")
    public JSONObject test3() throws InterruptedException {
        System.out.println("TEST3--------------------------");
        Thread.sleep(3000);
        JSONObject result = new JSONObject();
        result.put("code", "000000");
        result.put("msg", "test3");
        return result;
    }

    @GetMapping(value = "test4")
    public JSONObject test4() throws InterruptedException {
        System.out.println("TEST4------start");
        movieService.async();
        System.out.println("TEST4------end");
        JSONObject result = new JSONObject();
        result.put("code", "000000");
        result.put("msg", "test4");
        return result;
    }


    @GetMapping(value = {"/list"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult list() {
        List<Movie> movieList = movieService.list();
        return new ResponseResult(movieList);
    }

    //    @Cacheable(value = {"movie"}, key = "#pageNo/#pageSize")
    @GetMapping(value = {"/page"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult listWithPage(@RequestParam("pageNo") int pageNo,
                                       @RequestParam("pageSize") int pageSize,
                                       @RequestParam(value = "movieName",required = false) String   movieName) {
        Movie movie = new Movie();
        IPage<Movie> movieIPage = movieService.page(new Page<>(pageNo, pageSize),
                new QueryWrapper<Movie>().like(movieName!=null,"title",movieName));
        return new ResponseResult(movieIPage);
    }

    //    @Cacheable(value = {"movie"}, key = "#id")
    @GetMapping(value = {"/selectById/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
    public ResponseResult selectById(@PathVariable Long id) {
        return new ResponseResult(movieService.getById(id));
    }

//    @GetMapping(value = {"/stream/selectById/{id}"}, produces = {MediaType.APPLICATION_PROBLEM_JSON_UTF8_VALUE})
//    public ResponseResult selectById2(@PathVariable int id) {
//        return new ResponseResult(demoService.selectById(id));
//    }

    @GetMapping(value = {"/hi"})
    public String hi() {
        return "hi,i back home ! hn32297";
    }

}
