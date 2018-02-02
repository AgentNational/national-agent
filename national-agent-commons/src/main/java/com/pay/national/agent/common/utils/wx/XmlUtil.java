package com.pay.national.agent.common.utils.wx;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XmlUtil {


    /**
     * 处理xml请求信息
     *
     * @param request
     * @return
     */
    public static String getXmlRequest(HttpServletRequest request) {
        java.io.BufferedReader bis = null;
        String result = "";
        try {
            bis = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream()));
            String line = null;
            while ((line = bis.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
