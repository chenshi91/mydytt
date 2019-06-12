package com.dytt.common.utils;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;

public interface SendMailService {

    public void sendMail(SimpleMailMessage... message) throws MailException;

    public void sendExceptionMail(Exception exception) throws MailException;
}
