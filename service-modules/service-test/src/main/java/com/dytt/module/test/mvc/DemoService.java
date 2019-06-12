/* created by chenshi at 2019-02-02 */
package com.dytt.module.test.mvc;

import com.dytt.common.exception.ServiceException;
import com.dytt.common.mvc.BaseService;

public interface DemoService extends BaseService<Demo> {

    Demo selectByPrimaryKey2(Long id) throws ServiceException;
}
