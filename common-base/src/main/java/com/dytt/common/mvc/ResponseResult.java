/* created by chenshi at 2019-02-16 */
package com.dytt.common.mvc;

import com.dytt.common.constance.CommonResponse;
import lombok.Data;

import java.io.Serializable;

/**
 * 出参格式
 */
@Data
public class ResponseResult implements Serializable {

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

}