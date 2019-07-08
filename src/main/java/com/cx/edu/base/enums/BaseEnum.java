package com.cx.edu.base.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有Entity中枚举类型的基础, 主要用于数据库中存储枚举的value和SpringMVC参数直接转换成Enum
 */
public interface BaseEnum {

	/**
	 * JsonValue用于serializer
	 *
	 * @return
	 */
	@JsonValue
	int value();

	/**
	 * 获取枚举值对应的枚举
	 *
	 * @param enumClass 枚举类
	 * @param enumValue 枚举值
	 * @return 枚举
	 */
	static <E extends BaseEnum> E getEnum(final Class<E> enumClass, final Integer enumValue) {
		if (enumValue == null) {
			return null;
		}
		try {
			return valueOf(enumClass, enumValue);
		} catch (final IllegalArgumentException ex) {
			return null;
		}
	}

	/**
	 * 获取枚举值对应的枚举
	 *
	 * @param enumClass 枚举类
	 * @param enumValue 枚举值
	 * @return 枚举
	 */
	static <E extends BaseEnum> E valueOf(Class<E> enumClass, Integer enumValue) {
		if (enumValue == null) {
			throw new NullPointerException("EnumValue is null");
		}
		return getEnumMap(enumClass).get(enumValue);
	}

	/**
	 * 获取枚举键值对
	 *
	 * @param enumClass 枚举类型
	 * @return 键值对
	 */
	static <E extends BaseEnum> Map<Integer, E> getEnumMap(Class<E> enumClass) {
		E[] enums = enumClass.getEnumConstants();
		if (enums == null) {
			throw new IllegalArgumentException(enumClass.getSimpleName() + " does not represent an enum type.");
		}
		Map<Integer, E> map = new HashMap<>();
		for (E t : enums) {
			map.put(t.value(), t);
		}
		return map;
	}


}
