package me.dio.bankline.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import me.dio.bankline.databinding.ActivityBankStatementBinding
import me.dio.bankline.domain.Correntista
import me.dio.bankline.domain.Movimentacao
import me.dio.bankline.domain.TipoMovimentacao
import me.dio.bankline.ui.statement.adapters.BankStatementAdapter
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "me.dio.bankline.ui.statement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

       findBankStatement()
    }

    private fun findBankStatement() {
        val dataSet = ArrayList<Movimentacao>()
        dataSet.add(Movimentacao(1,"07/05/2022 13:46","Lorem Ipsum",550.25,TipoMovimentacao.DESPESA,2))
        dataSet.add(Movimentacao(2,"07/05/2022 13:47","Lorem Ipsum",1000.00,TipoMovimentacao.RECEITA,1))
        dataSet.add(Movimentacao(3,"07/05/2022 13:48","Lorem Ipsum",450.00,TipoMovimentacao.RECEITA,3))
        dataSet.add(Movimentacao(4,"07/05/2022 13:49","Lorem Ipsum",800.00,TipoMovimentacao.DESPESA,5))
        dataSet.add(Movimentacao(5,"07/05/2022 13:50","Lorem Ipsum",230.62,TipoMovimentacao.DESPESA,4))
        dataSet.add(Movimentacao(6,"07/05/2022 13:55","Lorem Ipsum",2500.00,TipoMovimentacao.RECEITA,2))
        binding.rvBankStatement.adapter = BankStatementAdapter(dataSet)
    }
}