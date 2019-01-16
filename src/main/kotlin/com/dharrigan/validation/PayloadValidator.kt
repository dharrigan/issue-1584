package com.dharrigan.validation

import com.dharrigan.model.Reprocess
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.stereotype.Component
import javax.validation.Validator

@Component
class PayloadValidator(private val objectMapper: ObjectMapper, private val validator: Validator) {

    fun validate(json: String): Reprocess {
        val reprocess: Reprocess = objectMapper.readValue(json)
        validator.validate(reprocess)
        return reprocess
    }
}
