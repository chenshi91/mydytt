/* created by chenshi at 2019-02-02 */
package com.cmss.test.mvc;

import com.dytt.common.model.exception.ServiceException;
import com.dytt.common.model.mvc.BaseMapper;
import com.dytt.common.model.mvc.BaseServiceImpl;
import com.cmss.test.mvc.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DemoServiceImpl extends BaseServiceImpl<Demo> implements DemoService {

    @Autowired
    DemoMapper demoMapper;

    @Override
    protected BaseMapper<Demo> getMapper() {
        return demoMapper;
    }


    @Override
    public Demo selectByPrimaryKey2(Long id) throws ServiceException {
        return demoMapper.selectByPrimaryKey(id);
    }
}
