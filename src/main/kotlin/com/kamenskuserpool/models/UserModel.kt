package com.kamenskuserpool.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Suppress("ktlint:standard:no-blank-line-in-list")
@Entity
@Table(name = "tb_user")
data class UserModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CUSTOMER")
    val id: Long,

    @Column(name = "COD_COSTUMER", nullable = false, unique = true)
    val customerId: String,

    @Column(name = "SAFEPAY_USER", nullable = false)
    val safepayUserId: Long,
    @Column(name = "CREDIT_FLAG", nullable = false)
    val creditFlg: Boolean,

    @Column(name = "ACCOUNT_FLAG")
    val accountFlg: Boolean,
    @Column(name = "PREPAID_FLAG")
    val prepaidFlg: Boolean,

    @Column(name = "CREATION_TIMESTAMP")
    @CreationTimestamp
    val dateCreation: LocalDateTime,

    @Column(name = "UPDATE_TIMESTAMP")
    @UpdateTimestamp
    val dateUpdate: LocalDateTime,

    @Column(name = "CPF")
    val cpf: String,
)
