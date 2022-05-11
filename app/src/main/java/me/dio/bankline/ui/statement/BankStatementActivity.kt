package me.dio.bankline.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import me.dio.bankline.R
import me.dio.bankline.data.State
import me.dio.bankline.data.room.MovimentacaoRoomViewModel
import me.dio.bankline.databinding.ActivityBankStatementBinding
import me.dio.bankline.domain.Correntista
import me.dio.bankline.ui.statement.adapters.BankStatementAdapter
import me.dio.bankline.ui.statement.adapters.BankStatementViewModel
import java.lang.Double.parseDouble
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

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

    private val viewModel by viewModels<BankStatementViewModel>()

    // Implementando Room
    //Para isso, nosso Repository pode evoluir negocialmente para expor os dados locais via LiveData através do Room.
    //Assim, o App poderá funcionar offline, apresentando as Movimentações da última requisição HTTP realizada com sucesso.

    private val movimentacaoRoomViewModel by lazy {
        ViewModelProvider(this).get(MovimentacaoRoomViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Log.e("CORRENTISTA", "Correntista : $accountHolder")

       binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

       findBankStatement(accountHolder.id)

        binding.srlBankStatement.setOnRefreshListener { findBankStatement(accountHolder.id) }
        
    }

    private fun findBankStatement(id: Int) {
        viewModel.findBankStatement(id).observe(this) { state ->
            when(state){
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement,it,Snackbar.LENGTH_LONG).show() }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Wait -> binding.srlBankStatement.isRefreshing = true

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val search = menu.findItem(R.id.searchBar)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                try{
                    val number = newText?.let { parseDouble(it) }
                    if(number != null && number > 0) findBankStatement(number.toInt())
                } catch (e: NumberFormatException) {
                    Snackbar.make(searchView,R.string.txt_number_error,Snackbar.LENGTH_LONG).show()
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}