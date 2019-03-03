/* created by chenshi at 2018-11-16 */
package com.cmss.dytt.movie.dao;

import java.util.List;

public abstract interface BaseDao<T> {

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Long id);

    List<T> selectList();

    List<T> selectByCondition(T t);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
