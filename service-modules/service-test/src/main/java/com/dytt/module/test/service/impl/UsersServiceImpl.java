package com.dytt.module.test.service.impl;

import com.dytt.module.test.entity.Users;
import com.dytt.module.test.dao.UsersMapper;
import com.dytt.module.test.service.UsersService;
import com.dytt.common.mvc.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenshi
 * @since 2019-06-20
 */
@Service
public class UsersServiceImpl extends BaseServiceImpl<UsersMapper, Users> implements UsersService {

}
