package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchCreditService
import com.kamenskuserpool.utils.DtoFactory
import com.kamenskuserpool.utils.UserFactory
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class SwitchCreditServiceTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var switchCreditService: SwitchCreditService

    @Test
    fun `shouldReturnCreditOnWhenOnWasRequested`() {

        val userOn = UserFactory.generateUserCreditOn()
        val dtoOn = DtoFactory.generateDtoCreditOn()


        every { userRepository.findByCustomerId(dtoOn.customerId) }.returns(userOn)
        every { userRepository.save(userOn) }.returns(userOn)

        val result = switchCreditService.switchCredit(dtoOn)

        assert(result == "Credit On")
        assert(userOn.creditFlg == true)
        verify(exactly = 1) { userRepository.save(userOn) }
    }

    @Test
    fun `shouldReturnCreditOffWhenffWasRequested`() {

        val userOff = UserFactory.generateUserCreditOff()
        val dtoOff = DtoFactory.generateDtoCreditOff()

        every { userRepository.findByCustomerId(dtoOff.customerId) }.returns(userOff)
        every { userRepository.save(userOff) }.returns(userOff)

        val result = switchCreditService.switchCredit(dtoOff)

        assert(result == "Credit Off")
        assert(!userOff.creditFlg)
        verify(exactly = 1) { userRepository.save(userOff) }
    }

    @Test
    fun `shouldThrowInvalidRequestException`() {

        val userException = UserFactory.generateUserCreditException()
        val dtoException = DtoFactory.generateDtoCreditException()

        every { userRepository.findByCustomerId(dtoException.customerId) }.returns(userException)
        every { userRepository.save(userException) }.returns(userException)

        assertThrows<SwitchFlagException> {
            switchCreditService.switchCredit(dtoException)
        }
    }
}
