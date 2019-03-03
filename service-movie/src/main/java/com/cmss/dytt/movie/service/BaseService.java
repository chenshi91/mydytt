package com.cmss.dytt.movie.service;

import com.cmss.dytt.movie.entity.Movie;

import java.util.List;

public abstract interface BaseService<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    T selectByPrimaryKey(Long id);

    List<T> selectByCondition(T t);

    int updateByPrimaryKey(T record);
}
