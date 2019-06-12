/* created by chenshi at 2019-02-27 */
package com.dytt.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    //定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public static void info(String msg, Object... objects) {
        logger().info(msg, objects);
    }

    private static Logger logger() {
        StackTraceElement caller = findCaller();
        String className = caller.getClassName();
        Logger logger = caller == null ? LoggerFactory.getLogger(LogUtil.class) : LoggerFactory.getLogger(caller.getClassName() + "." + caller.getMethodName() + "() [line:" + caller.getLineNumber() + "]");
        return logger;
    }

    private static StackTraceElement findCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (null == stackTrace) {
            return null;
        } else {
            StackTraceElement caller = null;
            String logClassName = LogUtil.class.getName();
            boolean isEachLogClass = false;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (logClassName.equals(stackTraceElement.getClassName())) {
                    isEachLogClass = true;
                }
                if (isEachLogClass && !logClassName.equals(stackTraceElement.getClassName())) {
                    caller = stackTraceElement;
                    break;
                }
            }
            return caller;
        }
    }

}
