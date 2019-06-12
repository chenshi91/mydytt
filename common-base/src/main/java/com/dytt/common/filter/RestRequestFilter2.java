/* created by chenshi at 2019-02-14 */
package com.dytt.common.filter;

import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

public class RestRequestFilter2 extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest httpServletRequest, String s) {

    }

    @Override
    protected void afterRequest(HttpServletRequest httpServletRequest, String s) {

    }
}
