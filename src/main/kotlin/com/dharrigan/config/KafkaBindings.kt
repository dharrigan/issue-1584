package com.dharrigan.config

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel
import org.springframework.stereotype.Component

@Component
interface KafkaBindings : Source {

    @Output(DISCARD)
    fun discard(): MessageChannel

    @Input(REPROCESS)
    fun reprocess(): SubscribableChannel

    companion object {
        const val DISCARD = "discard"
        const val REPROCESS = "reprocess"
    }

}
