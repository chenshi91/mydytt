/* created by chenshi at 2019-01-30 */
package com.dytt.common.model.constance;

import org.springframework.beans.factory.annotation.Value;

public class WebConstance {

    @Value("${web.requestId}")
    public static String REQUEST_ID = "dytt-requestId";

    @Value("${web.requestBody}")
    public static String REQUEST_BODY = "dytt-requestBody";
}
