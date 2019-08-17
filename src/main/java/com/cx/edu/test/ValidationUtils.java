package com.cx.edu.test;

import com.mysql.jdbc.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 验证工具类,脱离spring框架
 * @author cuixiao
 */
public class ValidationUtils {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 验证实体
     * @param obj
     * @param <T>
     * @return
     */
    public <T> ValidationResult validateEntity(T obj) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validate(obj, Default.class);
        if (set != null && set.size() != 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<String, String>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(cv.getPropertyPath().toString(), cv.getMessage());
                if (StringUtils.isNullOrEmpty(result.getFirstErrorMsg())) {
                    result.setFirstErrorMsg(cv.getMessage());
                }
            }
            result.setErrorMsg(errorMsg);
        }else {
            result.setHasErrors(false);
        }
        return result;
    }

    /**
     * 验证实体中某一个属性
     * @param obj
     * @param propertyName
     * @param <T>
     * @return
     */
    public <T> ValidationResult validateProperty(T obj, String propertyName) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<T>> set = validator.validateProperty(obj, propertyName, Default.class);
        if (set != null && set.size() != 0) {
            result.setHasErrors(true);
            Map<String, String> errorMsg = new HashMap<>();
            for (ConstraintViolation<T> cv : set) {
                errorMsg.put(propertyName, cv.getMessage());
            }
            result.setErrorMsg(errorMsg);
        }
        return result;
    }
}
