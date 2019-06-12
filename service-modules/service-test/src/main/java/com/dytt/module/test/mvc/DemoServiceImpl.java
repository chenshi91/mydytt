/* created by chenshi at 2019-02-02 */
package com.dytt.module.test.mvc;

import com.dytt.common.exception.ServiceException;
import com.dytt.common.mvc.BaseServiceImpl;
import com.dytt.module.test.mvc.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoMapper, Demo> implements DemoService {

    @Autowired
    DemoMapper demoMapper;


    @Override
    public Demo selectByPrimaryKey2(Long id) throws ServiceException {
        return demoMapper.selectById(id);
    }
}
