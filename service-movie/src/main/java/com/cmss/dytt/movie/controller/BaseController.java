/* created by chenshi at 2018-11-16 */
package com.cmss.dytt.movie.controller;

import com.alibaba.fastjson.JSONObject;
import com.cmss.dytt.movie.service.BaseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class BaseController<T> {
    abstract BaseService<T> getService();

    /**
     * 查详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = {"/detail/{id}"})
    public JSONObject detail(@PathVariable Long id) {
        T entity = getService().selectByPrimaryKey(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "000000");
        jsonObject.put("data", entity);
        return jsonObject;
    }

    /**
     * 查询列表
     *
     * @return
     */
    @GetMapping(value = {"/list"})
    public JSONObject list() {
        List list = getService().selectByCondition(null);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "000000");
        jsonObject.put("data", list);
        return jsonObject;
    }

    /**
     * 添加
     *
     * @param params
     * @return
     */
    @PostMapping(value = {"/insert"})
    public JSONObject insert(@RequestBody T params) {
        int insert = getService().insert(params);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "000000");
        jsonObject.put("data", insert);
        return jsonObject;
    }

    /**
     * 修改
     *
     * @param params
     * @return
     */
    @PostMapping(value = {"/update"})
    public JSONObject update(@RequestBody JSONObject params) {
        int update = getService().updateByPrimaryKey((T) params);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "000000");
        jsonObject.put("data", update);
        return jsonObject;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @GetMapping(value = {"/delete/{id}"})
    public JSONObject delete(@PathVariable Long id) {
        int delete = getService().deleteByPrimaryKey(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "000000");
        jsonObject.put("data", delete);
        return jsonObject;
    }


}
