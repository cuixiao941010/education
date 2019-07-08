package com.cx.edu.entity.university.enums;

import com.cx.edu.base.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum SubjectEnum implements BaseEnum {

	SCIENCE(1, "理科"),
	LIBERAL_ARTS(2, "文科");

	private Integer value;

	private String text;

	SubjectEnum(Integer value, String text) {
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
	public static BaseEnum valueOf(Integer value) {
		return BaseEnum.valueOf(BaseEnum.class, value);
	}
}
