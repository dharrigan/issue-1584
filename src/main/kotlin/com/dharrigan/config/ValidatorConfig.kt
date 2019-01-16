package com.dharrigan.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.validation.Validation
import javax.validation.Validator

@Configuration
class ValidatorConfig {

    @Bean
    fun validator(): Validator = Validation.buildDefaultValidatorFactory().validator

}
