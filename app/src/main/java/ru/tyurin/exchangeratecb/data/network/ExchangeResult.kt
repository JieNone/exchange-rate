package ru.tyurin.exchangeratecb.data.network

import ru.tyurin.exchangeratecb.domain.model.CurrencyResponse

sealed class ExchangeResult {
    data class Success(val data: CurrencyResponse) : ExchangeResult()
    data class Error(val errorMessage: String) : ExchangeResult()
    data object Loading : ExchangeResult()
}