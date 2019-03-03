/* created by chenshi at 2019-02-16 */
package com.cmss.dytt.common.web.mvc;

import com.cmss.dytt.common.web.constance.CommonResponse;

/**
 * 出参格式
 */
public class ResponseResult {

    private String code;
    private String msg;
    private Object data;
    private String port;
    private String applicationName;


    public ResponseResult(Object data) {
        this.code = CommonResponse.SUCCESS_CODE;
        this.msg = CommonResponse.SUCCESS_MSG;
        this.data = data;
    }

    public ResponseResult(String msg) {
        this.code = CommonResponse.SUCCESS_CODE;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
