package com.flowframe.sflow.core.util;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author faquan.dxj
 * @date 2022/8/31
 */
public class ValidateUtil {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    static String buildErrorMsg(String error, String info) {
        return error + "#" + info;
    }

    static String validateBeanObject(Object bean, Class<?>... groups) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> resultSet = validator.validate(bean, groups);
        return resultSet.isEmpty() ? null : (String)resultSet.stream().map((cv) -> {
            return cv.getPropertyPath() + "^" + cv.getMessage();
        }).collect(Collectors.joining(","));
    }

    public static void validate(Object bean, Class<?>... groups) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Object>> resultSet = validator.validate(bean, groups);
        if (!resultSet.isEmpty()) {
            String msg = resultSet.stream().map((cv) -> cv.getPropertyPath() + "^" + cv.getMessage())
                    .collect(Collectors.joining(","));
            throw new ConstraintViolationException(msg, resultSet);
        }
    }
}
