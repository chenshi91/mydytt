/* created by chenshi at 2018-11-16 */
package com.dytt.common.model.mvc;

import com.github.pagehelper.PageInfo;

import java.util.List;

public abstract class BaseController<T> {
    protected abstract BaseService<T> getService();

    /**
     * 查详情
     *
     * @param id
     * @return
     */
    public ResponseResult detail(Long id) {
        String simpleName = getService().getClass().getSimpleName();
        T entity = getService().selectByPrimaryKey(id);
        return new ResponseResult(entity);
    }

    /**
     * 查询列表
     *
     * @return
     */
    public ResponseResult list() {
        List list = getService().selectByCondition(null);
        return new ResponseResult(list);
    }

    /**
     * 分页查询
     *
     * @return
     */
    public ResponseResult list(int pageNo, int pageSize, T t) {
        PageInfo<T> pageInfo = getService().selectByConditionWithPage(pageNo, pageSize, t);
        return new ResponseResult(pageInfo);
    }

    /**
     * 添加
     *
     * @param params
     * @return
     */
    public ResponseResult insertSelective(T params) {
        getService().insertSelective(params);
        return new ResponseResult("添加数据成功!");
    }

    /**
     * 修改
     *
     * @param t
     * @return
     */
    public ResponseResult updateByPrimaryKeySelective(T t) {
        getService().updateByPrimaryKeySelective(t);
        return new ResponseResult("修改数据成功!");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public ResponseResult delete(Long id) {
        getService().deleteByPrimaryKey(id);
        return new ResponseResult("删除数据成功!");
    }


}
