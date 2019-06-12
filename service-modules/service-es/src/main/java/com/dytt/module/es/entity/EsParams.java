package com.dytt.module.es.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author chenshi
 * @date 2019-05-30
 */
@Data
public   class EsParams {
    private String  index;
    private String  type;
    private JSONObject source;

}
