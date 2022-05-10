package me.dio.bankline.data.remote

import me.dio.bankline.domain.Correntista
import me.dio.bankline.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineApi {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountHolderId: Int): List<Movimentacao>

    @GET("correntistas/{id}")
    suspend fun findCorrentistaById(@Path("id") correntistaId: Int): Correntista
}