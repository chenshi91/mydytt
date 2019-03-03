/* created by chenshi at 2018-11-10 */
package com.cmss.dytt.movie.service.impl;

import com.cmss.dytt.movie.dao.BaseDao;
import com.cmss.dytt.movie.dao.MovieMapper;
import com.cmss.dytt.movie.entity.Movie;
import com.cmss.dytt.movie.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(rollbackFor = Exception.class)
public class MovieServiceImpl extends BaseServiceImpl<Movie> implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Resource
    MovieMapper movieMapper;

    @Override
    BaseDao getMapper() {
        return movieMapper;
    }


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
}
