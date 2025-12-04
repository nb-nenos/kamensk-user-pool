package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.clients.PsCoreClient
import com.kamenskuserpool.exceptions.SafepayUserIdException
import com.kamenskuserpool.services.PsCoreService
import com.kamenskuserpool.utils.ResponseFactory
import com.kamenskuserpool.utils.UserFactory
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class PsCoreServiceTest(

    @MockK
    private val psCoreClient: PsCoreClient,

    @InjectMockKs
    private val psCoreService: PsCoreService
) {

    @Test
    fun `shouldReturnAResponseWhenExternalAPISucceeds`() {

        val dto = UserFactory.generateSafepayUserId()
        val response = ResponseFactory.generateSafepayAPIResponse()

        every { psCoreClient.getSafepayUserId(dto.email) } returns(response)

        val result = psCoreService.createSafepayUserId(dto)

        assert(result.safePayUserId == response.safePayUserId)
    }

    @Test
    fun `shouldThrowExceptionWhenExternalAPIFails`() {

        val dto = UserFactory.buildUserDto()

        every { psCoreClient.getSafepayUserId(dto.email) } throws RuntimeException("Erro na API")

        assertThrows ( SafepayUserIdException::class.java ) {
            psCoreService.createSafepayUserId(dto)
        }
    }
}
