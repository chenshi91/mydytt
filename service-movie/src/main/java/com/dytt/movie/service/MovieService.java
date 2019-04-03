/* created by chenshi at 2018-11-10 */
package com.dytt.movie.service;

;
import com.dytt.movie.entity.Movie;
import com.dytt.common.model.mvc.BaseService;
import com.dytt.common.model.mvc.ResponseResult;

public interface MovieService extends BaseService<Movie> {

    public void async();

    ResponseResult listByPage(int pageNo, int pageSize, Movie movie);

}
