/**
 * Created	by	chenshi  at	2018年1月5日 下午1:33:21
 */
package com.dytt.common.exception;

/**
 * @description: ServiceException.java
 * @packageName: com.quanhu.base.exception
 * @projectName: quanhu-base
 * @revision: v1.0.0
 * @author: chenshi
 */
public class ServiceException extends RuntimeException {

    /***/
    private static final long serialVersionUID = -7137961117203665574L;

    private String message;

    /**
     * @param message
     */
    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
