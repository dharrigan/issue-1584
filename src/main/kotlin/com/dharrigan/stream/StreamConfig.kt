package com.dharrigan.stream

import com.dharrigan.config.KafkaBindings.Companion.REPROCESS
import com.dharrigan.handler.PayloadHandler
import com.dharrigan.model.Reprocess
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.context.annotation.Configuration

@Configuration
class StreamConfig(
    private val payloadHandler: PayloadHandler,
    private val objectMapper: ObjectMapper
) {

    @StreamListener(REPROCESS)
    fun reprocess(reprocess: Reprocess) {
        payloadHandler.handle(objectMapper.writeValueAsString(reprocess))
    }

}
