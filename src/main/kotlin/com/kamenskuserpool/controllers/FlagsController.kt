package com.kamenskuserpool.controllers

import com.kamenskuserpool.dtos.RequestSwitchAccountDto
import com.kamenskuserpool.dtos.RequestSwitchCreditDto
import com.kamenskuserpool.dtos.RequestSwitchPrepaidDto
import com.kamenskuserpool.services.SwitchAccountService
import com.kamenskuserpool.services.SwitchCreditService
import com.kamenskuserpool.services.SwitchPrepaidService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/flags")
class FlagsController(
    private val switchPrepaidService: SwitchPrepaidService,
    private val switchCreditService: SwitchCreditService,
    private val switchAccountService: SwitchAccountService
) {

    @PostMapping("/prepaidUser")
    fun switchPrepaid(@RequestBody switchPrepaidPayload: RequestSwitchPrepaidDto): ResponseEntity<String> {
        val prepaidStatus = switchPrepaidService.switchPrepaid(switchPrepaidPayload)
        return ResponseEntity.status(HttpStatus.OK).body(prepaidStatus)
    }

    @PostMapping("/creditUser")
    fun switchCredit(@RequestBody switchCreditPayload: RequestSwitchCreditDto): ResponseEntity<String> {
        val creditStatus = switchCreditService.switchCredit(switchCreditPayload)
        return ResponseEntity.status(HttpStatus.OK).body(creditStatus)
    }

    @PostMapping("/userAccount")
    fun switchAccount(@RequestBody switchAccountPayload: RequestSwitchAccountDto): ResponseEntity<String> {
        val accountStatus = switchAccountService.switchAccount(switchAccountPayload)
        return ResponseEntity.status(HttpStatus.OK).body(accountStatus)
    }
}
