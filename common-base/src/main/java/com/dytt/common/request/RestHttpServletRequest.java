/* created by chenshi at 2019-01-30 */
package com.dytt.common.request;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RestHttpServletRequest extends HttpServletRequestWrapper {

    public RestHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    public String getRequestBody() {
        try {
            ServletInputStream is = super.getRequest().getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = "";
            StringBuffer body = new StringBuffer();
            while ((line = br.readLine()) != null) {
                body.append(line);
            }
            br.close();
            is.close();
            return body.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            is=null;

        }
        return null;
    }


}
