/* created by chenshi at 2019-02-26 */
package com.dytt.web;

import com.alibaba.fastjson.JSONObject;
import com.cmss.dytt.common.web.mvc.ResponseResult;
import com.dytt.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MovieController {

    @Autowired
    MovieService    movieService;
    @Autowired
    RestTemplate    restTemplate;


    @GetMapping(value = "/first")
    public String   first(HttpServletRequest request){
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        request.setAttribute("name","zhang4");
        return "demo";
    }

    @GetMapping(value = "/movies/{pageNo}/{pageSize}")
    public String   movies(@PathVariable int  pageNo, @PathVariable int  pageSize,HttpServletRequest request){
        ResponseEntity<JSONObject> result = restTemplate.getForEntity(
                "http://ZUUL/api-movie/list/"+pageNo+"/"+pageSize, JSONObject.class);
        JSONObject list = result.getBody();
//        ResponseResult list = movieService.listWithPage(pageNo,pageSize);
        request.setAttribute("result",list);
        if (!list.getString("code").equals("000000")) {
            return "error";
        }

        return "movie/movies";
    }

    @GetMapping(value = "/movie/{id}")
    public String   movie(@PathVariable Long  id, Model model){
        ResponseEntity<JSONObject> result = restTemplate.getForEntity(
                "http://ZUUL/api-movie/selectById/"+id, JSONObject.class);
//        ResponseResult entity = movieService.selectById(id);
        model.addAttribute("result",result.getBody());
        if (!result.getBody().getString("code").equals("000000")) {
            return "error";
        }
        return "movie/movie";
    }
}
