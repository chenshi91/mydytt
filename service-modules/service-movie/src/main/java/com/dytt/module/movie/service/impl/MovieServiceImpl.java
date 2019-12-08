/* created by chenshi at 2018-11-10 */
package com.dytt.module.movie.service.impl;


import com.dytt.common.mvc.BaseServiceImpl;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.module.movie.mapper.MovieMapper;
import com.dytt.module.movie.entity.Movie;
import com.dytt.module.movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(rollbackFor = Exception.class)
public class MovieServiceImpl extends BaseServiceImpl<MovieMapper, Movie> implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Resource
    MovieMapper movieMapper;


    @Async(value = "movieThreadPool")
    @Override
    public void async() {
        logger.info("start ------async()");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("end ------async()");
    }

    @Override
    public ResponseResult listByPage(int pageNo, int pageSize, Movie movie) {
//        PageHelper.startPage(pageNo, pageSize);
//        List<Movie> list = movieMapper.selectByCondition(movie);
        return new ResponseResult(null);
    }

}
