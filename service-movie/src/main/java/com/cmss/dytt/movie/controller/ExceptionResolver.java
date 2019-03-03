/* created by chenshi at 2018-12-17 */
package com.cmss.dytt.movie.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
public class ExceptionResolver {

    @ExceptionHandler(RuntimeException.class)
    public Object exceptionHandler1(RuntimeException exception, HttpServletRequest request, HttpServletResponse response) {
        JSONObject errorResult = new JSONObject();
        if (exception.getClass().isAssignableFrom(DataAccessException.class)) {
            //dao层出现异常
            errorResult.put("code", "947001");
            errorResult.put("msg", "数据库操作出现异常");
        }
        return errorResult;

    }


}
