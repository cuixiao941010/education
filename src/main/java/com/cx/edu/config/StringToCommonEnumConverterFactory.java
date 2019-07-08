package com.cx.edu.config;

import com.cx.edu.base.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.StringUtils;

public class StringToCommonEnumConverterFactory implements ConverterFactory<String, BaseEnum> {

	@Override
	public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnum<>(targetType);
	}

	private static class StringToEnum<T extends BaseEnum> implements Converter<String, T> {

		private final Class<T> enumType;

		public StringToEnum(Class<T> enumType) {
			this.enumType = enumType;
		}

		@Override
		public T convert(String source) {
			if (StringUtils.isEmpty(source)) {
				return null;
			}
			return BaseEnum.getEnum(enumType, Integer.valueOf(source.trim()));
		}
	}

}
