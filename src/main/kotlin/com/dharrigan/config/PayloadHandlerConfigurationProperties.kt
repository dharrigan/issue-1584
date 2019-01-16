package com.dharrigan.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import java.time.Duration

@ConfigurationProperties("service-bus.handler")
data class PayloadHandlerConfigurationProperties(
    var maxConcurrentCalls: Int = 1,
    var isAutoComplete: Boolean = true,
    var maxAutoRenewDuration: Duration = Duration.ofMinutes(2)
)
