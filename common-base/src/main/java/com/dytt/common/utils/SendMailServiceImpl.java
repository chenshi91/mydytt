/* created by chenshi at 2019-02-20 */
package com.dytt.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.dytt.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

public class SendMailServiceImpl implements SendMailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String mailFrom;
    @Value("${common.mail.sendTo}")
    String mailTo;
    @Value("${common.mail.title}")
    String mailTitle;

    @Async(value = "asyncThreadPool")
    @Override
    public void sendMail(SimpleMailMessage... message) throws MailException {
        javaMailSender.send(message);
    }

    @Async(value = "asyncThreadPool")
    @Override
    public void sendExceptionMail(Exception exception) throws MailException {
        JSONObject exceptionMsg = null;
        StackTraceElement[] stackTraceElements = exception.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            String elementClassName = stackTraceElement.getClassName();
            if (!StringUtils.isEmpty(elementClassName) && elementClassName.startsWith("com.cmss")) {
                if (exceptionMsg == null) {
                    exceptionMsg = new JSONObject();
                    exceptionMsg.put("exceptionClass", exception.getClass());
                }
                exceptionMsg.put("exceptionFrom" + System.currentTimeMillis(), JsonUtil.parseJsonObject(stackTraceElement));
            }
        }

        //发邮件
        SimpleMailMessage message = new SimpleMailMessage();
        if (StringUtils.isEmpty(mailTo)) {
            throw new ServiceException("发邮件收件地址mailTo为空");
        } else if (mailTo.contains(",")) {
            message.setTo(mailTo.split(","));
        } else {
            message.setTo(mailTo);
        }
        message.setFrom(mailFrom);
        message.setSubject(mailTitle);
        message.setText(exceptionMsg != null ? exceptionMsg.toJSONString() : exception.getMessage());
        this.sendMail(message);
    }
}
