package com.dharrigan

import com.dharrigan.config.CircularStreamProperties
import com.dharrigan.config.KafkaBindings
import com.dharrigan.config.PayloadHandlerConfigurationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@SpringBootApplication
@EnableBinding(KafkaBindings::class)
@EnableConfigurationProperties(CircularStreamProperties::class, PayloadHandlerConfigurationProperties::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
