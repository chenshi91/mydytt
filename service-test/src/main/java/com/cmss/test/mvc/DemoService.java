/* created by chenshi at 2019-02-02 */
package com.cmss.test.mvc;

import com.cmss.dytt.common.web.exception.ServiceException;
import com.cmss.dytt.common.web.mvc.BaseService;

public interface DemoService extends BaseService<Demo> {

    Demo selectByPrimaryKey2(Long id) throws ServiceException;
}
