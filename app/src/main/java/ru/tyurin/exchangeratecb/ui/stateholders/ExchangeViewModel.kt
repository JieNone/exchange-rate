package ru.tyurin.exchangeratecb.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import ru.tyurin.exchangeratecb.data.network.ExchangeResult
import ru.tyurin.exchangeratecb.data.repository.ExchangeRepository
import javax.inject.Inject


class ExchangeViewModel @Inject constructor(
    private val exchangeRepository: ExchangeRepository
) : ViewModel() {

    private val _exchangeList = MediatorLiveData<ExchangeResult>()
    val exchangeList: LiveData<ExchangeResult>
        get() = _exchangeList

    init {
        fetchCurrentExchangeWithDelay()
    }

    private fun fetchCurrentExchangeWithDelay() {
        viewModelScope.launch {
            while (true) {
                _exchangeList.value = ExchangeResult.Loading
                fetchCurrentExchange()
                delay(30000)
            }
        }
    }

    private fun fetchCurrentExchange() {
        viewModelScope.launch {
            val result = exchangeRepository.loadExchange()
            _exchangeList.value = result
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
    }
}