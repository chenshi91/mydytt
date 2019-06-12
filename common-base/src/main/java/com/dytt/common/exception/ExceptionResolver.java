/* created by chenshi at 2018-12-17 */
package com.dytt.common.exception;

import com.dytt.common.mvc.ResponseResult;
import com.dytt.common.utils.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class ExceptionResolver {

    @Autowired
    SendMailService sendMailService;

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler1(Exception exception, HttpServletRequest request, HttpServletResponse response) {
        ResponseResult errorResult =null;
//        MethodArgumentNotValidException
        if (exception instanceof RuntimeException) {
            //dao层出现异常
            errorResult=new ResponseResult("947001","数据库操作出现异常");
        } else if (exception instanceof ServiceException) {
            //service层出现异常
            errorResult=new ResponseResult("947002","service出现异常");
        } else if (exception instanceof ControllerException) {
            errorResult=new ResponseResult("947003","controller出现异常");
        } else if (exception instanceof MailException) {
            errorResult=new ResponseResult("947004","邮件发送出现异常");
        }else if (exception instanceof MethodArgumentNotValidException){
            String defaultMessage = ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors().get(0).getDefaultMessage();
            errorResult=new ResponseResult("947005","入参字段校验异常:".concat(defaultMessage));

        }else {
            errorResult=new ResponseResult("947006","未知异常");
        }
        //发邮件
        sendMailService.sendExceptionMail(exception);
        return errorResult;

    }

}
