package com.kamenskuserpool.repositories

import com.kamenskuserpool.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserModel, Long>
