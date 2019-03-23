package com.dytt.common.model.mvc;

import com.dytt.common.model.exception.ServiceException;
import com.github.pagehelper.PageInfo;

import java.util.List;

public abstract interface BaseService<T> {

    void deleteByPrimaryKey(Long id) throws ServiceException;

    void insert(T record) throws ServiceException;

    void insertSelective(T record) throws ServiceException;

    T selectByPrimaryKey(Long id) throws ServiceException;

    List<T> selectByCondition(T t) throws ServiceException;

    PageInfo<T> selectByConditionWithPage(int pageNo, int pageSize, T t) throws ServiceException;

    void updateByPrimaryKey(T record) throws ServiceException;

    void updateByPrimaryKeySelective(T record) throws ServiceException;


}
