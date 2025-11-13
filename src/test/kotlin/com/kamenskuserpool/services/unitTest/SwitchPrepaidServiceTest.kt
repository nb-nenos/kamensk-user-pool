package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.exceptions.SwitchFlagException
import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchPrepaidService
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
class SwitchPrepaidServiceTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var switchPrepaidService: SwitchPrepaidService

    @Test
    fun `shouldReturnPrepaidOnWhenOnWasRequested`() {

        val user = UserFactory.generateUserPrepaidOn()
        val dto = DtoFactory.generateDtoPrepaidOn()

        every { userRepository.findByCustomerId(dto.customerId) }.returns(user)
        every { userRepository.save(user) }.returns(user)

        val result = switchPrepaidService.switchPrepaid(dto)

        assert(result == "Prepaid On")
        assert(user.prepaidFlg == true)
        verify(exactly = 1) { userRepository.save(user) }
    }

    @Test
    fun `shouldReturnPrepaidOffWhenOffWasRequested`() {

        val user = UserFactory.generateUserPrepaidOff()
        val dto = DtoFactory.generateDtoPrepaidOff()

        every { userRepository.findByCustomerId(dto.customerId) }.returns(user)
        every { userRepository.save(user) }.returns(user)

        val result = switchPrepaidService.switchPrepaid(dto)

        assert(result == "Prepaid Off")
        assert(!user.prepaidFlg)
        verify(exactly = 1) { userRepository.save(user) }
    }

    @Test
    fun `shouldThrowInvalidRequestException`() {

        val user = UserFactory.generateUserPrepaidException()
        val dto = DtoFactory.generateDtoPrepaidException()

        every { userRepository.findByCustomerId(dto.customerId) }.returns(user)
        every { userRepository.save(user) }.returns(user)

        assertThrows<SwitchFlagException> {
            switchPrepaidService.switchPrepaid(dto)
        }
    }
}
