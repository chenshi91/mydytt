/* created by chenshi at 2018-11-16 */
package com.cmss.dytt.movie.service.impl;

import com.cmss.dytt.movie.dao.BaseDao;
import com.cmss.dytt.movie.service.BaseService;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    abstract BaseDao<T> getMapper();

    @Override
    public int deleteByPrimaryKey(Long id) {
        return getMapper().deleteByPrimaryKey(id);
    }

    @Override
    public int insert(T record) {
        int insert = getMapper().insert(record);
        return insert;
    }

    @Override
    public T selectByPrimaryKey(Long id) {
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectByCondition(T object) {
        return getMapper().selectByCondition(object);
    }

    ;

    @Override
    public int updateByPrimaryKey(T record) {
        return getMapper().updateByPrimaryKey(record);
    }
}
