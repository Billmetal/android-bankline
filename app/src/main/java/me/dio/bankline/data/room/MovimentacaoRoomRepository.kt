package me.dio.bankline.data.room

import android.util.Log
import androidx.lifecycle.liveData
import me.dio.bankline.data.State
import java.lang.Exception

class MovimentacaoRoomRepository(private val movimentacaoDAO: MovimentacaoDAO) {

    fun addMovimentacoesRoom(movimentacoes: List<MovimentacaoRoom>) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(movimentacaoDAO.addMovimentacoesRoom(movimentacoes)))
        } catch (e: Exception) {
            Log.e("ROOM-REPO",e.message,e)
            emit(State.Error(e.message))
        }
    }

    fun readAllMovimentacaoRoom() = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = movimentacaoDAO.readAllMovimentacaoRoom()))
        } catch (e: Exception) {
            Log.e("ROOM-REPO",e.message,e)
            emit(State.Error(e.message))
        }
    }

    fun readMovimentacaoRoomById(idConta: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = movimentacaoDAO.readMovimentacaoRoomById(idConta)))
        } catch (e: Exception) {
            Log.e("ROOM-REPO",e.message,e)
            emit(State.Error(e.message))
        }
    }
}