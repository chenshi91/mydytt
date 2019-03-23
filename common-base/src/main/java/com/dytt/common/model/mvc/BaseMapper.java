/* created by chenshi at 2018-11-16 */
package com.dytt.common.model.mvc;

import java.util.List;

public abstract interface BaseMapper<T> {

    int deleteByPrimaryKey(Long id) throws RuntimeException;

    int insert(T record) throws RuntimeException;

    int insertSelective(T record) throws RuntimeException;

    T selectByPrimaryKey(Long id) throws RuntimeException;

    List<T> selectList() throws RuntimeException;

    List<T> selectByCondition(T t) throws RuntimeException;

    int updateByPrimaryKeySelective(T record) throws RuntimeException;

    int updateByPrimaryKey(T record) throws RuntimeException;
}
