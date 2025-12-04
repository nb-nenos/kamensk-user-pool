package com.kamenskuserpool.integrationTest

import com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.client.WireMock.verify
import com.kamenskuserpool.AbstractIntegrationTest
import com.kamenskuserpool.services.UserService
import com.kamenskuserpool.utils.stubs.StubCustomerAPI
import com.kamenskuserpool.utils.stubs.StubPsCore
import org.springframework.beans.factory.annotation.Autowired
import kotlin.test.Test


class UserServiceIntegrationTest: AbstractIntegrationTest() {

    @Autowired
    lateinit var userService: UserService


    @Test
    fun `should create an user in database`() {
        StubCustomerAPI().stubCustomerAPI()
        StubPsCore().stubPsCore("test@email.com")

        userService.createUser()

        verify(postRequestedFor(urlEqualTo("/create-user")))

    }
}
