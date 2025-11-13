package com.kamenskuserpool.clients

import com.kamenskuserpool.dtos.ResponseCustomerAPIDto
import com.kamenskuserpool.dtos.UserDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "customer-api", url = "\${kamensk-user-pool.integrations.client.customer-api.uri}")
interface CustomerAPIClient {

    @PostMapping("/create-user")
    fun createUser(@RequestBody payload: UserDto): ResponseCustomerAPIDto
}
