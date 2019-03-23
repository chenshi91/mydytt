/* created by chenshi at 2018-11-16 */
package com.dytt.common.model.mvc;


import com.dytt.common.model.exception.ServiceException;
import com.dytt.common.model.log.AddLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    protected abstract BaseMapper<T> getMapper();

    @AddLog
    @Override
    public void deleteByPrimaryKey(Long id) throws ServiceException {
        try {
            getMapper().deleteByPrimaryKey(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ServiceException("-----deleteByPrimaryKey()-----sql执行出现异常:"+e.getMessage());
        }
    }

    @AddLog
    @Override
    public void insert(T record) throws ServiceException {
        try {
            getMapper().insert(record);
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new ServiceException("-----insert()-----sql执行出现异常:"+e.getMessage());
        }
    }

    @AddLog
    @Override
    public void insertSelective(T record) throws ServiceException {
        getMapper().insertSelective(record);
    }

    @AddLog
    @Override
    public T selectByPrimaryKey(Long id) throws ServiceException {
        return getMapper().selectByPrimaryKey(id);
    }

    @AddLog
    @Override
    public List<T> selectByCondition(T object) throws ServiceException {
        return getMapper().selectByCondition(object);
    }

    @AddLog
    @Override
    public PageInfo<T> selectByConditionWithPage(int pageNo, int pageSize, T t) throws ServiceException {
        PageHelper.startPage(pageNo,pageSize);
        List<T> list = getMapper().selectByCondition(t);
        return new PageInfo<T>(list);
    }

    ;

    @AddLog
    @Override
    public void updateByPrimaryKey(T record) throws ServiceException {
        getMapper().updateByPrimaryKey(record);
    }

    @AddLog
    @Override
    public void updateByPrimaryKeySelective(T record) throws ServiceException {
        getMapper().updateByPrimaryKeySelective(record);
    }
}
