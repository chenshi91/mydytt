package com.dytt.web.api;

import com.dytt.module.user.fallback.UserApiFeignFallback;
import com.dytt.module.user.feign.UserApiFeign;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(value = "GATEWAY",fallback = UserApiFeignFallback.class)
public interface UserApi extends UserApiFeign {

}
