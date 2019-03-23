/* created by chenshi at 2018-12-17 */
package com.dytt.common.model.exception;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.model.utils.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
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

    @Autowired
    SendMailService sendMailService;

    @ExceptionHandler(RuntimeException.class)
    public Object exceptionHandler1(RuntimeException exception, HttpServletRequest request, HttpServletResponse response) {
        JSONObject errorResult = new JSONObject();

        if (exception instanceof RuntimeException) {
            //dao层出现异常
            errorResult.put("code", "947001");
            errorResult.put("msg", "数据库操作出现异常");
        } else if (exception instanceof ServiceException) {
            //service层出现异常
            errorResult.put("code", "947002");
            errorResult.put("msg", "service出现异常");
        } else if (exception instanceof ControllerException) {
            //service层出现异常
            errorResult.put("code", "947003");
            errorResult.put("msg", "controller出现异常");
        }else if (exception instanceof MailException){
            errorResult.put("code", "947004");
            errorResult.put("msg", "发送邮件出现异常:{}"+exception);
        }

        //发邮件
        sendMailService.sendExceptionMail(exception);
        return errorResult;

    }

}
