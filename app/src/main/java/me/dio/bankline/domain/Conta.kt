package me.dio.bankline.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Conta(
    val numero: Long,
    val saldo: Double
) : Parcelable
