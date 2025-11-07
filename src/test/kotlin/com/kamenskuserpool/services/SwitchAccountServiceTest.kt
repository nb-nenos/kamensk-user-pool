package com.kamenskuserpool.services

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.exceptions.InvalidRequestException
import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.utils.UserFactory
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID
import kotlin.test.Test


@ExtendWith(MockKExtension::class)
class SwitchAccountServiceTest(

    @MockK
    private val userRepository: UserRepository,

    @InjectMockKs
    private val switchAccountService: SwitchAccountService
){

    @Test
    fun `shouldReturnAccountOnWhenOnWasRequested`() {

        val user = UserFactory.generateUser()

        val dto = RequestSwitchAccountDto(
            customerId = user.customerId,
            switchAccount = "on"
        )

        every { userRepository.findByCustomerId(dto.customerId) }.returns(user)
        every { userRepository.save(user) }.returns(user)
        every { userRepository.save(user) }.returns(user)

        val result = switchAccountService.switchAccount(dto)

        assert(result == "Account on")
    }

    @Test
    fun `shouldReturnAccountOffWhenOffWasRequested`() {

        val userOff = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Ana",
            safepayUserId = 3,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "09876543212",
        )

        val dtoOff = RequestSwitchAccountDto(
            customerId = userOff.customerId,
            switchAccount = "off"
        )

        every { userRepository.findByCustomerId(dtoOff.customerId) }.returns(userOff)
        every { userRepository.save(userOff) }.returns(userOff)
        every { userRepository.save(userOff) }.returns(userOff)

        val resultOff = switchAccountService.switchAccount(dtoOff)

        assert(resultOff == "Account off")
    }

    @Test
    fun `shouldThrowInvalidRequestException`() {

        val user = UserModel(
            customerId = UUID.randomUUID().toString(),
            fullName = "Carol",
            safepayUserId = 4,
            creditFlg = false,
            accountFlg = false,
            prepaidFlg = false,
            cpf = "90876512354"
        )

        val dto = RequestSwitchAccountDto(
            customerId = UUID.randomUUID().toString(),
            switchAccount = "batata"
        )

        every { userRepository.findByCustomerId(dto.customerId) }.returns(user)
        every { userRepository.save(user) }.returns(user)
        every { userRepository.save(user) }.returns(user)

        assertThrows<InvalidRequestException> {
            switchAccountService.switchAccount(dto)
        }
    }
}
