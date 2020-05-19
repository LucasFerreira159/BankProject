package com.app4funbr.bank.model

import java.io.Serializable

data class Account (
    val userId: Int,
    val name: String,
    val bankAccount: String,
    val agency: String,
    val balance: Double
): Serializable