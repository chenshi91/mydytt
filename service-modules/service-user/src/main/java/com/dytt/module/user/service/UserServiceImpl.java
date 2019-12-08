/* created by chenshi at 2019-02-03 */
package com.dytt.module.user.service;

import com.dytt.common.mvc.BaseServiceImpl;
import com.dytt.module.user.mapper.UserMapper;
import com.dytt.module.user.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl  extends BaseServiceImpl<UserMapper, User>   implements UserService {

}
