package me.dio.bankline.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.dio.bankline.domain.TipoMovimentacao

@Entity(tableName = "movimentacoes_table")
data class MovimentacaoRoom(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val dataHora: String,
    val descricao: String,
    val valor: Double,
    val tipo: TipoMovimentacao,
    val idConta: Int
)
