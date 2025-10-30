package com.kamenskuserpool.schedulers

import com.kamenskuserpool.services.UserService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class UserJob(private val userService: UserService) {

    @Scheduled(cron = "\${kamensk-user-pool.scheduled.cron.user}")
    fun createUser() {
        userService.createUser()
    }
}
