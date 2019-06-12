/* created by chenshi at 2019-02-03 */
package com.dytt.module.user.mvc;

import com.dytt.common.mvc.BaseServiceImpl;
import com.dytt.module.user.dao.UserDao;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {
}
