package com.kamenskuserpool.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("kamensk-user-pool.info")
data class InfoProperties(
    val fullName: String,
    val dateOfBirth: String,
    val email: String,
    val password: String,
    val motherName: String,
    val safetyPhrase: String
)
