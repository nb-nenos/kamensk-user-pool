package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.exceptions.InvalidRequestException
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchAccountService
import com.kamenskuserpool.utils.DtoFactory
import com.kamenskuserpool.utils.UserFactory
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
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

        val userOn = UserFactory.generateUserAccountOn()

        val dtoOn = DtoFactory.generateDtoAccountOn()

        every { userRepository.findByCustomerId(dtoOn.customerId) }.returns(userOn)
        every { userRepository.save(userOn) }.returns(userOn)
        every { userRepository.save(userOn) }.returns(userOn)

        val result = switchAccountService.switchAccount(dtoOn)

        assert(result == "Account on")
    }

    @Test
    fun `shouldReturnAccountOffWhenOffWasRequested`() {

        val userOff = UserFactory.generateUserAccountOff()

        val dtoOff = DtoFactory.generateDtoAccountOff()

        every { userRepository.findByCustomerId(dtoOff.customerId) }.returns(userOff)
        every { userRepository.save(userOff) }.returns(userOff)
        every { userRepository.save(userOff) }.returns(userOff)

        val resultOff = switchAccountService.switchAccount(dtoOff)

        assert(resultOff == "Account off")
    }

    @Test
    fun `shouldThrowInvalidRequestException`() {

        val userException = UserFactory.generateUserAccountException()

        val dtoException = DtoFactory.generateDtoAccountException()

        every { userRepository.findByCustomerId(dtoException.customerId) }.returns(userException)
        every { userRepository.save(userException) }.returns(userException)

        assertThrows<InvalidRequestException> {
            switchAccountService.switchAccount(dtoException)
        }
    }
}
