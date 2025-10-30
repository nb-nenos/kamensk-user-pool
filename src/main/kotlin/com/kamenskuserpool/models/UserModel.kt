package com.kamenskuserpool.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.UUID

@Suppress("ktlint:standard:no-blank-line-in-list")
@Entity
@Table(name = "tb_user")
data class UserModel(

    @Id
    @Column(name = "ID_CUSTOMER")
    val id: UUID = UUID.randomUUID(),

    @Column(name = "COD_COSTUMER", nullable = false, unique = true)
    val customerId: String = "",

    @Column(name = "FULL_NAME")
    val fullName: String = "",

    @Column(name = "SAFEPAY_USER", nullable = false)
    val safepayUserId: Long = 0,

    @Column(name = "CREDIT_FLAG", nullable = false)
    val creditFlg: Boolean = false,

    @Column(name = "ACCOUNT_FLAG")
    val accountFlg: Boolean = false,

    @Column(name = "PREPAID_FLAG")
    val prepaidFlg: Boolean = false,

    @Column(name = "CREATION_TIMESTAMP")
    @CreationTimestamp
    val dateCreation: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATE_TIMESTAMP")
    @UpdateTimestamp
    val dateUpdate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "CPF")
    val cpf: String = "",
)
