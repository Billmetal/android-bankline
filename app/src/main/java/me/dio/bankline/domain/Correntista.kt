package me.dio.bankline.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Correntista(
    val id: Int,
    val nome: String,
    val conta: Conta
) : Parcelable
