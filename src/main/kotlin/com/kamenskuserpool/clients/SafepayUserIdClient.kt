package com.kamenskuserpool.clients

import com.kamenskuserpool.dtos.ResponseSafepayUserIdDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "safepay-api", url = "\${kamensk-user-pool.integrations.client.safepay-api.uri}")
interface SafepayUserIdClient {

    @GetMapping("/{email}")
    fun getSafepayUserId(@PathVariable email: String): ResponseSafepayUserIdDto
}
