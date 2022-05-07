package me.dio.bankline.ui.statement.adapters

import androidx.lifecycle.ViewModel
import me.dio.bankline.data.BanklineRepository

class BankStatementViewModel : ViewModel() {

    fun findBankStatement(accountHolderId: Int) = BanklineRepository.findBankStatement(accountHolderId)
}