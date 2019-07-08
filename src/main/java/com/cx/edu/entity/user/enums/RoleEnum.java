package com.cx.edu.entity.user.enums;

import com.cx.edu.base.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum RoleEnum implements BaseEnum {

	Simple(1, "普通角色");

	private Integer value;

	private String text;

	RoleEnum(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	@Override
	public int value() {
		return value;
	}

	/**
	 * mybatis Mapper中的方法参数如果为枚举的话, 会调用toString()方法获取枚举值, 用于ognl表达式判断
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return String.valueOf(this.value);
	}

	@SuppressWarnings("unused")
	@JsonCreator
	public static RoleEnum valueOf(Integer value) {
		return BaseEnum.valueOf(RoleEnum.class, value);
	}
}
