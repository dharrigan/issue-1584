package com.dharrigan.handler

import com.dharrigan.model.Discard
import com.dharrigan.router.PayloadGatewayRouter
import com.dharrigan.validation.PayloadValidator
import com.microsoft.azure.servicebus.ExceptionPhase
import com.microsoft.azure.servicebus.IMessage
import com.microsoft.azure.servicebus.IMessageHandler
import mu.KotlinLogging
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

private val LOGGER = KotlinLogging.logger {}

@Component
class PayloadHandler(private val payloadGatewayRouter: PayloadGatewayRouter, private val payloadValidator: PayloadValidator) : IMessageHandler {

    override fun onMessageAsync(message: IMessage?): CompletableFuture<Void> {
        TODO("not implemented")
    }

    override fun notifyException(exception: Throwable?, phase: ExceptionPhase?) {
        TODO("not implemented")
    }

    fun handle(message: String) {
        val reprocess = payloadValidator.validate(message)
        payloadGatewayRouter.discard(Discard(reprocess.message))
    }

}
