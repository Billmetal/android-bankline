package me.dio.bankline.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import me.dio.bankline.R
import me.dio.bankline.databinding.ActivityWelcomeBinding
import me.dio.bankline.domain.Correntista
import me.dio.bankline.ui.statement.BankStatementActivity
import java.lang.Double
import java.lang.NumberFormatException

class WelcomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            //TODO Melhoria (difícil): evoluir a API para recuperar um Correntista por ID, permitindo assim o envio de mais informações para a próxima tela.

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
        val accountHolder = Correntista(accountHolderId)

        val intent = Intent(this,BankStatementActivity::class.java).apply {
            putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER,accountHolder)
        }
        startActivity(intent)
    }
}