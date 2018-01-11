package com.pay.national.agent.common.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;

import com.pay.national.agent.common.annotation.NoJson;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: json工具类
 * @see: 需要参考的类
 * @version 2017年9月4日 下午5:28:25
 * @author zhenhui.liu
 */
public class JSONUtils {

	public static final Set<Class<?>> beanClasses;
	
	private static volatile ObjectMapper mapper = new ObjectMapper();
//	private static final Logger log = Logger.getLogger(JSONUtil.class);
	static{
		beanClasses = ClassUtil.getClasses("com.pay.pluton.api.bean");
		beanClasses.addAll(ClassUtil.getClasses("com.pay.pluton.model.entity"));
		beanClasses.addAll(ClassUtil.getClasses("com.pay.pluton.model.beans"));
	}

	public static JSONObject fromObject(Object obj) {
		JSONObject jsonObject = new JSONObject();
		Field[] fields;
		Object o;
		if (obj != null) {
			try {
				fields = obj.getClass().getDeclaredFields();
				for (Field f : fields) {
					if(f.getAnnotation(NoJson.class) != null){
						continue;
					}
					f.setAccessible(true);
					o = f.get(obj);
					if (o != null ) {
						if (o instanceof ArrayList) {
							jsonObject.element(f.getName(), fromArray(o));
						} else if (beanClasses.contains(o.getClass())) {
							jsonObject.element(f.getName(), fromObject(o));
						} else if (o instanceof Date) {
							Long l = ((Date) o).getTime();
							String time = l.toString();
							if("00000".equals(time.substring(time.length()-5,time.length()))){
								jsonObject.put(f.getName(),
										SimpleDateUtils.formatDate((Date) o));
							}else{
								jsonObject.put(f.getName(),
										SimpleDateUtils.formatWithTime((Date) o));
							}
						}else if(o instanceof BigDecimal){
							jsonObject.put(f.getName(), ((BigDecimal) o).setScale(2, BigDecimal.ROUND_HALF_UP)+"");
						} else {
							jsonObject.put(f.getName(),o.toString());
						}
					} else {
						jsonObject.put(f.getName(),"");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("JSON格式转换失败！");
			}

		}
		return jsonObject;
	}

	@SuppressWarnings("rawtypes")
	public static JSONArray fromArray(Object arrayList) throws IllegalArgumentException, IllegalAccessException{
		List list;
		JSONArray jsonArray;
		if(arrayList != null){
			jsonArray = new JSONArray();
			if(arrayList instanceof ArrayList){
				list = (List) arrayList;
				for(int i=0; i< list.size(); i++){
					if(list.get(i) instanceof ArrayList){
						jsonArray.add(i,fromArray(list.get(i)));
					}else if(beanClasses.contains(list.get(i).getClass())) {
						jsonArray.add(i,fromObject(list.get(i)));
					 } else {
						jsonArray.add(i,String.valueOf(list.get(i)));
					 }
				}
				return jsonArray;
			} else {
				return null;
			}
		}else{
			return null;
		}
	}

	/**
	 * @param object
	 *            任意对象
	 * @return java.lang.String
	 */
	public static String objectToJson(Object object) {
		StringBuilder json = new StringBuilder();
		if (object == null) {
			json.append("\"\"");
		} else if (object instanceof String || object instanceof Integer || object instanceof Double) {
			json.append("\"").append((String)object).append("\"");
		} else {
			json.append(beanToJson(object));
		}
		return json.toString();
	}
	/**
	 * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串
	 *
	 * @param bean
	 *            bean对象
	 * @return String
	 */
	public static String beanToJson(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = objectToJson(props[i].getName());
					String value = objectToJson(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}
	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串
	 *
	 * @param list
	 *            列表对象
	 * @return java.lang.String
	 */
	public static String listToJson(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(objectToJson(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}
	


	public static ObjectMapper getInstance() {
		return mapper;
	}

	public static byte[] toJsonBytes(Object obj) {
		try {
			return mapper.writeValueAsBytes(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String toJsonString(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String alibabaJsonString(Object obj)
	{
		return com.alibaba.fastjson.JSON.toJSONString(obj);
	}

	public static <T> T toObject(String json, Class<T> clazz) {
		if (json == null) return null;
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}



	public static <T> T toObject(byte[] json, Class<T> clazz) {
		if (json == null) return null;
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	



	public static void main(String[] args) {
		BigDecimal b = new BigDecimal(123.00000);
		System.out.println(b.setScale(2,BigDecimal.ROUND_HALF_UP));
	}

}
