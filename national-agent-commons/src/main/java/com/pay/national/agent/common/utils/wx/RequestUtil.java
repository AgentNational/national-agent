package com.pay.national.agent.common.utils.wx;

import com.pay.national.agent.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class RequestUtil {

    /**
     * 获取当前请求链接
     *
     * @param request
     * @return
     */
    public static String getCurrentUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String requestParammStr = getRequestParammStr(request);
        if (!StringUtils.isEmpty(requestParammStr)) {
            url += "?" + requestParammStr;
        }
        return url;
    }

    /**
     * 获取request参数
     *
     * @param request
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static String getRequestParammStr(HttpServletRequest request) {
        Map requestParams = request.getParameterMap();
        StringBuffer paramStr = new StringBuffer();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            if (paramStr.toString() != null && !paramStr.toString().equals("")) {
                paramStr.append("&");
            }
            // 乱码解决，出现乱码时使用
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            try {
                valueStr = URLEncoder.encode(valueStr, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            paramStr.append(name).append("=").append(valueStr);

        }
        return paramStr.toString();
    }

    /**
     * @Description map转换为key=value&key=value格式，并且自然排序
     * @param requestParam
     * @return
     * @throws UnsupportedEncodingException
     * @see 需要参考的类或方法
     */
    public static String MapToStrSort(Map<String, String> requestParam)  {
        StringBuffer strbuff = new StringBuffer();
        List<String> list = new ArrayList<String>(requestParam.keySet());
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            String paramName = list.get(i);
            String paramValue = requestParam.get(paramName);
            strbuff.append(paramName).append("=").append(paramValue);
            if (i != list.size() - 1) {
                strbuff.append("&");
            }
        }
        return strbuff.toString();
    }

    /**
     * 参数URLEncoder encode
     *
     * @param paramMap
     * @param encode
     * @return
     * @throws UnsupportedEncodingException
     */
    public static Map<String, String> paramToURLEncoder(Map<String, String> paramMap, String encode)
            throws UnsupportedEncodingException {
        for (String key : paramMap.keySet()) {
            paramMap.put(key, URLEncoder.encode(paramMap.get(key), encode));
        }
        return paramMap;
    }

    /**
     * url参数拼接
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String paramToUrl(String url, Map<String, String> paramMap) {
        url += "?";
        for (String key : paramMap.keySet()) {
            url += "&" + key + "=" + paramMap.get(key);
        }
        return url;
    }

    /**
     * 获取客户端ip
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
