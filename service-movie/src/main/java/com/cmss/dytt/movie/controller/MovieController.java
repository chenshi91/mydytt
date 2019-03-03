/* created by chenshi at 2018-11-10 */
package com.cmss.dytt.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmss.dytt.movie.entity.Movie;
import com.cmss.dytt.movie.service.BaseService;
import com.cmss.dytt.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
public class MovieController extends BaseController<Movie> {

    @Override
    BaseService getService() {
        return movieService;
    }

    @Autowired
    MovieService movieService;


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


}
