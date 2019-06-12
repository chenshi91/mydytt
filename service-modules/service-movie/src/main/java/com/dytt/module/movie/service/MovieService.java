/* created by chenshi at 2018-11-10 */
package com.dytt.module.movie.service;

import com.dytt.common.mvc.BaseService;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.module.movie.entity.Movie;

;

public interface MovieService extends BaseService<Movie> {

    public void async();

    ResponseResult listByPage(int pageNo, int pageSize, Movie movie);

}
