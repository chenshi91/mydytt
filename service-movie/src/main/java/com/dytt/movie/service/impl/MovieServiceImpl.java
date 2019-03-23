/* created by chenshi at 2018-11-10 */
package com.dytt.movie.service.impl;


import com.dytt.common.model.mvc.BaseMapper;
import com.dytt.common.model.mvc.BaseServiceImpl;
import com.dytt.common.model.mvc.ResponseResult;
import com.dytt.movie.dao.MovieMapper;
import com.dytt.movie.entity.Movie;
import com.dytt.movie.service.MovieService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

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
        PageHelper.startPage(pageNo,pageSize);
        List<Movie> list = movieMapper.selectByCondition(movie);
        return new ResponseResult(new PageInfo<Movie>(list));
    }


    @Override
    protected BaseMapper<Movie> getMapper() {
        return movieMapper;
    }
}
