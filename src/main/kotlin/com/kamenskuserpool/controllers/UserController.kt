package com.kamenskuserpool.controllers

import com.kamenskuserpool.models.UserModel
import com.kamenskuserpool.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    @GetMapping("/users/random")
    fun getRandomUsers(): ResponseEntity<UserModel> {
        val randomUser = userService.getRandomUsers()
        return ResponseEntity.status(HttpStatus.OK).body(randomUser)
    }

    @GetMapping("/users/with-credit")
    fun getUserWithCredit(): ResponseEntity<UserModel>{
        val creditUser = userService.getUserWithCredit()
        return ResponseEntity.status(HttpStatus.OK).body(creditUser)
    }

    @GetMapping("/users/with-prepaid")
    fun getPrepaidUser(): ResponseEntity<UserModel> {
        val prepaidUser = userService.getPrepaidUser()
        return ResponseEntity.status(HttpStatus.OK).body(prepaidUser)
    }
}
