package com.dharrigan.router

import com.dharrigan.config.KafkaBindings.Companion.DISCARD
import org.springframework.integration.annotation.Gateway
import org.springframework.integration.annotation.MessagingGateway
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
@MessagingGateway
interface PayloadGateway {

    @Gateway(requestChannel = DISCARD)
    fun discard(@Header(PARTITION_KEY) partitionKey: String, message: String)

    companion object {
        const val PARTITION_KEY = "PARTITION_KEY"
    }

}
