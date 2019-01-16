package com.dharrigan.router

import com.dharrigan.model.Discard
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class PayloadGatewayRouter(private val payloadGateway: PayloadGateway, val objectMapper: ObjectMapper) {

    fun discard(discard: Discard) {
        payloadGateway.discard(Random.nextInt().toString(), discard.asJsonString())
    }

    fun Discard.asJsonString(): String = objectMapper.writeValueAsString(this)

}
