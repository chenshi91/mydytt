package com.dytt.module.user.fallback;

import com.dytt.common.feign.CommonFeignFallback;
import com.dytt.common.mvc.ResponseResult;
import com.dytt.module.user.feign.UserApiFeign;
import com.dytt.module.user.form.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserApiFeignFallback extends CommonFeignFallback implements UserApiFeign {

    @Override
    public ResponseResult selectWithPage(Integer pageNo, Integer pageSize, String nickName) {
        log.error("");
        return super.feignFailback("UserApiFeign.selectWithPage()");
    }

    @Override
    public ResponseResult getById(Long id) {
        log.error("");
        return super.feignFailback("UserApiFeign.getById()");
    }

    @Override
    public ResponseResult insert(UserForm userForm) {
        log.error("");
        return super.feignFailback("UserApiFeign.insert()");
    }

    @Override
    public ResponseResult update(UserForm userForm) {
        log.error("");
        return super.feignFailback("UserApiFeign.update()");
    }

    @Override
    public ResponseResult delete(Long id) {
        log.error("");
        return super.feignFailback("UserApiFeign.delete()");
    }
}
