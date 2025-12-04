package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.clients.CustomerAPIClient
import com.kamenskuserpool.exceptions.ClientException
import com.kamenskuserpool.services.CustomerAPIService
import com.kamenskuserpool.utils.ResponseFactory
import com.kamenskuserpool.utils.UserFactory
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class CustomerAPIServiceTest(

    @MockK
    private val customerAPIClient: CustomerAPIClient,

    @InjectMockKs
    private val customerAPIService: CustomerAPIService
) {

    @Test
    fun `shouldReturnResponseWhenAPIReturnsSuccess`() {

        val user = UserFactory.buildUserDto()
        val response = ResponseFactory.generateCustomerAPIResponse()

        every { customerAPIClient.createUser(user) } returns response

        val result =  customerAPIService.createUser(user)

        assertEquals(response, result)
    }

    @Test
    fun `shouldThrowExceptionWhenAPIFails`() {

        val user = UserFactory.buildUserDto()

        every { customerAPIClient.createUser(user) } throws RuntimeException("API error")

        assertThrows(ClientException::class.java) {
            customerAPIService.createUser(user)
        }
    }
}
