package me.dio.bankline.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.dio.bankline.domain.Movimentacao

@Dao
interface MovimentacaoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun addMovimentacoesRoom(list: List<MovimentacaoRoom>)

    @Query("SELECT * FROM movimentacoes_table ORDER BY id ASC")
    fun readAllMovimentacaoRoom(): List<MovimentacaoRoom>

    @Query("SELECT * FROM movimentacoes_table WHERE idConta IN (:idConta)")
    fun readMovimentacaoRoomById(idConta: Int): List<MovimentacaoRoom>
}