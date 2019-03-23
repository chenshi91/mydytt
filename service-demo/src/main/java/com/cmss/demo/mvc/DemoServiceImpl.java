/* created by chenshi at 2019-02-02 */
package com.cmss.demo.mvc;

import com.cmss.demo.mvc.mapper.DemoMapper;
import com.dytt.common.model.mvc.BaseMapper;
import com.dytt.common.model.mvc.BaseServiceImpl;
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


}
