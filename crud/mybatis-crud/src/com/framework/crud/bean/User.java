package com.framework.crud.bean;

import com.framework.base.BaseEntity;
import com.framework.core.annotation.Code;
import com.framework.core.annotation.Primary;
import com.framework.core.annotation.TableName;
import com.framework.core.annotation.TempField;

/**
 * 用户Bean
 * 
 * @author yyf
 * 
 */
@TableName(name = "user")
public class User extends BaseEntity {
	/**
	 * 用户名 /唯一
	 */
	@Primary
	private String name;
	/**
	 * 用户编码 /唯一/U开头
	 */
	@Code(name = "U")
	@Primary
	private String code;
	/**
	 * 用户年龄/非数据库字段
	 */
	@TempField
	private String age;
	/**
	 * 用户联系方式
	 */
	private String phone;
	/**
	 * 用户备注
	 */
	private String remark;

	public void setUser(String name, String age, String phone, String remark) {
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.remark = remark;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
