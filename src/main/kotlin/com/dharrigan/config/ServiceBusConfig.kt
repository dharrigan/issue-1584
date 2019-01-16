package com.dharrigan.config

import com.microsoft.azure.servicebus.IMessageHandler
import com.microsoft.azure.servicebus.ISubscriptionClient
import com.microsoft.azure.servicebus.MessageHandlerOptions
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import java.util.concurrent.ForkJoinPool

@Configuration
@Profile("!noazure")
class ServiceBusConfig {

    @Bean
    fun handlerOptions(properties: PayloadHandlerConfigurationProperties) =
        MessageHandlerOptions(properties.maxConcurrentCalls, properties.isAutoComplete, properties.maxAutoRenewDuration)

    @Bean
    fun clientApplicationRunner(client: ISubscriptionClient, payloadHandler: IMessageHandler, handlerOptions: MessageHandlerOptions) =
        ApplicationRunner { client.registerMessageHandler(payloadHandler, handlerOptions, ForkJoinPool.commonPool()) }

}
