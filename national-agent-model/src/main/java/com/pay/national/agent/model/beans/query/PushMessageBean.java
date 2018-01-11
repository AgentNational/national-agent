package com.pay.national.agent.model.beans.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送消息参数bean
 * @author shuyan.qi
 * Date:2017年9月7日下午11:47:38
 */
public class PushMessageBean implements Serializable{
	private static final long serialVersionUID = -4176570076747982872L;
	
	private String user_no;//用户编号（必填）
	private String app;//app在消息中心配置的唯一标识（必填）
	private String template_id;//模板id，非模板消息为空 
	private String title;//消息标题，非模板消息提供
	private String push_bar_txt;//通知栏内容，非模板消息提供
	private String content_txt;//消息正文，非模板消息提供
	
	/**
	 * 模板参数，以英文分号（；）分隔，模板消息提供，用于依次替换模板中的变量：
		1：消息标题title
		2：通知栏文本push_bar_txt
		3：消息正文content_txt
	 */
	private String template_parms;
	
	/**
	 * 指定发送时间(不指定默认为请求时间)，模板不支持夜间发送时，指定夜间时间无效s
	 */
	private Date plan_time;
	
	/**
	 * 消息中心是否展示（拉取）。0：不展示；1展示。不传默认展示
	 */
	private String center_show;
	
	/**
	 * 是否推送（通知栏展示）。0：不推送；1：推送。不传默认推送
	 */
	private String 	is_push;
	
	/**
	 * 二级json参数名称	参数值
		   type	        nodata	          无需跳转时，传递此值
		                native	          跳转原生模块
						link	          跳转链接
						 
	       redirect	    model	          模块标识，如Pay通首页值为HOME 
		                 url	          链接形式
		                 
	       keys	        nodata	         跳转无需传递参数，传递此值
		            key1;key2;key3	"跳转携带参数名称，分号分隔，解析后根据此值从redirect_params获取相应value值"
	       key1		
	       key2		
	       key3		
	       …	
	       
	       	
	       	例子：{"por2":"value2","keys":"pro1;pro2;pro3;","redirect":"NoticeModel","pro1":"value1","por3":"value3","type":"native"}
	 */
	private String redirect_params;

	public String getRedirect_params() {
		return redirect_params;
	}

	public void setRedirect_params(String redirect_params) {
		this.redirect_params = redirect_params;
	}

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPush_bar_txt() {
		return push_bar_txt;
	}

	public void setPush_bar_txt(String push_bar_txt) {
		this.push_bar_txt = push_bar_txt;
	}

	public String getContent_txt() {
		return content_txt;
	}

	public void setContent_txt(String content_txt) {
		this.content_txt = content_txt;
	}

	public String getTemplate_parms() {
		return template_parms;
	}

	public void setTemplate_parms(String template_parms) {
		this.template_parms = template_parms;
	}

	public Date getPlan_time() {
		return plan_time;
	}

	public void setPlan_time(Date plan_time) {
		this.plan_time = plan_time;
	}

	public String getCenter_show() {
		return center_show;
	}

	public void setCenter_show(String center_show) {
		this.center_show = center_show;
	}

	public String getIs_push() {
		return is_push;
	}

	public void setIs_push(String is_push) {
		this.is_push = is_push;
	}

	@Override
	public String toString() {
		return "PayMessageBean [user_no=" + user_no + ", app=" + app + ", template_id=" + template_id + ", title="
				+ title + ", push_bar_txt=" + push_bar_txt + ", content_txt=" + content_txt + ", template_parms="
				+ template_parms + ", plan_time=" + plan_time + ", center_show=" + center_show + ", is_push=" + is_push
				+ "]";
	}

}
