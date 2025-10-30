package com.kamenskuserpool.repositories

import com.kamenskuserpool.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
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
            WHERE creditFlg=true
            """,
        nativeQuery = true
    )
    fun findByCreditTrue(): UserModel?

    @Query(
        """
            SELECT *
            FROM tb_user
            WHERE prepaidFlg=true
            """,
        nativeQuery = true
    )
    fun findByIsPrepaidTrue(): UserModel?
}
