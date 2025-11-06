package com.kamenskuserpool.repositories

import com.kamenskuserpool.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface UserRepository : JpaRepository<UserModel, UUID> {

    @Query(
        """
            SELECT * 
            FROM  tb_user
            ORDER BY RANDOM() 
            LIMIT 1
            """,
        nativeQuery = true
    )
    fun findRandomUser(): UserModel?

    @Query(
        """
            SELECT * 
            FROM tb_user
            WHERE credit_flag = true
            ORDER BY RANDOM()
            LIMIT 1
            """,
        nativeQuery = true
    )
    fun findByCreditTrue(): UserModel?

    @Query(
        """
            SELECT *
            FROM tb_user
            WHERE prepaid_flag = true
            ORDER BY RANDOM()
            LIMIT 1
            """,
        nativeQuery = true
    )
    fun findByIsPrepaidTrue(): UserModel?

    fun findByCustomerId(@Param("customerId")customerId: String): UserModel?
}
