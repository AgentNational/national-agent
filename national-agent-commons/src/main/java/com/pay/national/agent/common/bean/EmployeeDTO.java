package com.pay.national.agent.common.bean;

import java.io.Serializable;

import com.pay.national.agent.common.annotation.ModelProp;
import com.pay.national.agent.common.annotation.ModelTitle;
public class EmployeeDTO extends ImportModel implements Serializable{
	  
	private static final long serialVersionUID = -8296912822041330169L;
	
	private Long id;
    @ModelProp(name = "电话", colIndex = 1, nullable = false)
    private String telephone;
 
    @ModelProp(name = "名称", colIndex = 0, nullable = false)
    private String name;

    @ModelProp(name = "性别", colIndex = 2, nullable = false)
    private String sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", telephone=" + telephone + ", name=" + name + ", sex=" + sex + "]";
	}
    
    
}
