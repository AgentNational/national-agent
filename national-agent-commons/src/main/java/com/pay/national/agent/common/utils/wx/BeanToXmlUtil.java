package com.pay.national.agent.common.utils.wx;

import com.pay.national.agent.common.bean.wx.EnterPrisePaymentBean;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

public class BeanToXmlUtil {


    /**
     * 企业付款对象转换成xml
     *
     * @param
     *
     * @return xml
     */
    public static String EnterPrisePaymentToXml(EnterPrisePaymentBean enterPrisePaymentBean) {
        xstream.alias("xml", enterPrisePaymentBean.getClass());
        return xstream.toXML(enterPrisePaymentBean);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     *
     * @date 2013-05-19
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
                    super.startNode(name, clazz);
                }

                @Override
                public String encodeNode(String name) {
                    return name;
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });

}
