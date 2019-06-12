/*created by chenshi at   2019/4/27*/
package com.dytt.module.movie.resource;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.utils.JsonUtil;
import com.dytt.module.movie.entity.Movie;
import com.dytt.module.movie.service.MovieService;
import com.dytt.bridge.services.MovieRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieResource implements MovieRemoteService {
    @Autowired
    MovieService movieService;

    @Override
    public JSONObject selectById(int id) {
        Movie movie = movieService.getById(id);
        JSONObject jsonObject = JsonUtil.parseJsonObject(movie);
        return jsonObject;
    }
}
