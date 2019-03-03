/* created by chenshi at 2019-02-26 */
package com.dytt.web;

import com.alibaba.fastjson.JSONObject;
import com.dytt.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MovieController {

    @Autowired
    MovieService    movieService;

    @GetMapping(value = "/first")
    public String   first(HttpServletRequest request){
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        request.setAttribute("name","zhang4");
        return "demo";
    }

    @GetMapping(value = "/movies")
    public String   movies(HttpServletRequest request){
        JSONObject list = movieService.list();
        request.setAttribute("list",list);
        return "movies";
    }
}
