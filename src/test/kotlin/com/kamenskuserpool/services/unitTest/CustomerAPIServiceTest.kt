package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.services.CustomerAPIService
import com.kamenskuserpool.utils.DtoFactory
import com.kamenskuserpool.utils.UserFactory
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class CustomerAPIServiceTest {

    @MockK
    lateinit var customerAPIService: CustomerAPIService

    @InjectMockKs
    lateinit var creditServiceTest: SwitchCreditServiceTest

    @Test
    fun `shouldReturnAResponseWhenClientRespondWithSuccess`() {

        val user = UserFactory

        val dto = DtoFactory.generateDtoCustomerAPI()

        val result = customerAPIService.createUser(dto)
    }
}
