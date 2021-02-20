package com.fastinjava.application.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * validator 配置
 *
 * @author bennett
 * @date 2020/12/8
 */
@Configuration
public class ValidatorConfiguration {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 快速失败
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}