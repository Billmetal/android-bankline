package me.dio.bankline.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import me.dio.bankline.R
import me.dio.bankline.data.State
import me.dio.bankline.databinding.ActivityWelcomeBinding
import me.dio.bankline.domain.Correntista
import me.dio.bankline.ui.statement.BankStatementActivity
import me.dio.bankline.ui.statement.adapters.BankStatementAdapter
import me.dio.bankline.ui.statement.adapters.BankStatementViewModel
import java.lang.Double
import java.lang.NumberFormatException

class WelcomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<WelcomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            val accountHolderId = verifyCorrentistaId()
            if(accountHolderId > 0) next(accountHolderId)
        }
    }

    fun verifyCorrentistaId() : Int {
        return try {
            binding.etAccountHolderId.text.toString().toInt()
        } catch(e: Exception) {
            Snackbar.make(binding.etAccountHolderId,R.string.txt_number_error, Snackbar.LENGTH_LONG).show()
            0
        }
    }

    fun next(accountHolderId: Int) {

        viewModel.findCorrentistaById(accountHolderId).observe(this) { state ->
            when(state){
                is State.Success -> {
                    state.data?.let {
                        val intent = Intent(this,BankStatementActivity::class.java).apply {
                            putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER,it)
                        }
                        startActivity(intent)
                    }
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.tilAccountHolderId,R.string.txt_accountHolder_null,Snackbar.LENGTH_LONG).show() }
                }
                is  State.Wait -> Snackbar.make(binding.tilAccountHolderId,R.string.txt_waiting,Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}