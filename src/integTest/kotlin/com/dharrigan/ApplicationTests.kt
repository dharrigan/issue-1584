package com.dharrigan

import com.microsoft.azure.servicebus.ISubscriptionClient
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest
@Suppress("unused")
@RunWith(SpringRunner::class)
class ApplicationTests {

    // mock to prevent creation of client
    @MockBean
    private lateinit var client: ISubscriptionClient

    @Test
    fun `context loads`() {
    }

}
