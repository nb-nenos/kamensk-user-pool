package com.kamenskuserpool.services.unitTest

import com.kamenskuserpool.repositories.UserRepository
import com.kamenskuserpool.services.SwitchPrepaidService
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class SwitchPrepaidServiceTest {

    @MockK
    lateinit var userRepository: UserRepository,

    @InjectMockKs
    lateinit var switchPrepaidService: SwitchPrepaidService

    @Test
    fun `shouldReturnPrepaidOnWhenOnwasRequested`() {

        val userOn =
    }

}
