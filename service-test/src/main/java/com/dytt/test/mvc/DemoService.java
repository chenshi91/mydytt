/* created by chenshi at 2019-02-02 */
package com.dytt.test.mvc;

import com.dytt.common.model.exception.ServiceException;
import com.dytt.common.model.mvc.BaseService;

public interface DemoService extends BaseService<Demo> {

    Demo selectByPrimaryKey2(Long id) throws ServiceException;
}
