package com.dytt.gateway.base;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chenshi
 * @date 2019-06-13
 */
@Slf4j
public class GatewayBaseTest extends BaseTest {

    @Override
    protected ClassLoader getClassLoader() {
        return GatewayBaseTest.class.getClassLoader();
    }

    @Override
    protected String getAppUserInfoKey() {
//        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(super.ipHost + "/user/api/v1/auth/cellphone/login/13554048574", JSONObject.class, "");
//        HttpHeaders headers = responseEntity.getHeaders();
//        String appuserinfo_key = Optional.ofNullable(headers).map(h -> h.get("app-user-info-key")).map(list -> list.get(0)).orElse("unKnow");
//        log.info("------lom------成功获取到app_userinfo_key:{}", appuserinfo_key);
        return "";
    }

    @Override
    protected String getSucResponseCode() {
        return "000000";
    }

}
