/* created by chenshi at 2019-02-15 */
package com.dytt.common.model.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil extends Date {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期转换
     *
     * @param dateString
     * @return
     */
    public static Date parseToDate(String dateString) {
        /*if (StringUtils.isEmpty(dateString)) {
            throw new ParseException("");
        }*/
        Date date = null;
        if (!dateString.contains(":")) {
            try {
                date = new SimpleDateFormat(YYYY_MM_DD).parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }

        try {
            date = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
}
