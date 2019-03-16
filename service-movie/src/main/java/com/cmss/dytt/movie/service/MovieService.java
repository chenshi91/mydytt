/* created by chenshi at 2018-11-10 */
package com.cmss.dytt.movie.service;

import com.cmss.dytt.common.web.mvc.BaseService;
import com.cmss.dytt.common.web.mvc.ResponseResult;
import com.cmss.dytt.movie.entity.Movie;

public interface MovieService extends BaseService<Movie> {

    public void async();

    ResponseResult  listByPage(int pageNo,int pageSize,Movie movie);

}
