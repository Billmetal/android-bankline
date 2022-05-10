package me.dio.bankline.ui.welcome

import androidx.lifecycle.ViewModel
import me.dio.bankline.data.BanklineRepository

class WelcomeViewModel : ViewModel(){

    fun findCorrentistaById(correntistaId: Int) = BanklineRepository.findCorrentistaById(correntistaId)
}