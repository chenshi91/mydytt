/* created by chenshi at 2018-11-16 */
package com.cmss.dytt.common.web.mvc;

import org.springframework.dao.DataAccessException;

import java.util.List;

public abstract interface BaseMapper<T> {

    int deleteByPrimaryKey(Long id) throws DataAccessException;

    int insert(T record) throws DataAccessException;

    int insertSelective(T record) throws DataAccessException;

    T selectByPrimaryKey(Long id) throws DataAccessException;

    List<T> selectList() throws DataAccessException;

    List<T> selectByCondition(T t) throws DataAccessException;

    int updateByPrimaryKeySelective(T record) throws DataAccessException;

    int updateByPrimaryKey(T record) throws DataAccessException;
}
