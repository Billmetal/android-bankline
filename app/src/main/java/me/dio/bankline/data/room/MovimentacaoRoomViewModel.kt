package me.dio.bankline.data.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import me.dio.bankline.domain.Movimentacao

class MovimentacaoRoomViewModel(application: Application): AndroidViewModel(application) {

    private val repository by lazy {
        MovimentacaoRoomRepository(MovimentacaoRoomDatabase.getDatabase(application).movimentacaoDAO())
    }

    fun addMovimentacoesRoom(movimentacoes: List<MovimentacaoRoom>) = repository.addMovimentacoesRoom(movimentacoes)

    fun readAllMovimentacaoRoom() = repository.readAllMovimentacaoRoom()

    fun readMovimentacaoRoomById(idConta: Int) = repository.readMovimentacaoRoomById(idConta)
}