/* created by chenshi at 2019-02-16 */
package com.dytt.common.mvc;

import com.dytt.common.constance.ResponseResultConstance;
import lombok.Data;

import java.io.Serializable;

/**
 * 出参格式
 */
@Data
public class ResponseResult<T> implements Serializable {

    private String code;
    private String msg;
    private T data;
    private String port;
    private String applicationName;


    public ResponseResult(T data) {
        this.code = ResponseResultConstance.SUCCESS_CODE;
        this.msg = ResponseResultConstance.SUCCESS_MSG;
        this.data = data;
    }

    public ResponseResult(String msg) {
        this.code = ResponseResultConstance.SUCCESS_CODE;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}