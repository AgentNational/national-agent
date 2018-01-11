package com.pay.national.agent.common.bean;

import java.io.Serializable;

import com.pay.national.agent.common.annotation.ModelProp;

/**
 * @Description: 
 * @see: 需要参考的类
 * @version 2017年9月25日 上午9:23:00
 * @author zhenhui.liu
 */
public class CreditCardImportBean extends ImportModel implements Serializable{
	private static final long serialVersionUID = -3581220108126724904L;
	private Long id;
    @ModelProp(name = "电话", colIndex = 1, nullable = false)
    private String phone;
 
    @ModelProp(name = "名称", colIndex = 0, nullable = false)
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CreditCardImportBean [id=" + id + ", phone=" + phone + ", name=" + name + "]";
	}

    
}
