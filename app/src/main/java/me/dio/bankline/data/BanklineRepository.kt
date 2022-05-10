package me.dio.bankline.data

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import me.dio.bankline.data.remote.BanklineApi
import me.dio.bankline.data.room.MovimentacaoDAO
import me.dio.bankline.data.room.MovimentacaoRoomViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

object BanklineRepository {

    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanklineApi::class.java)
    }

    fun findBankStatement(accountHolderId: Int) = liveData {
            emit(State.Wait)
            try {
                emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
            } catch (e: Exception) {
                Log.e(TAG,e.message,e)
                emit(State.Error(e.message))
            }
    }

    fun findCorrentistaById(idCorrentista: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = restApi.findCorrentistaById(idCorrentista)))
        } catch (e: Exception) {
            emit(State.Error(e.message))
        }
    }

}
