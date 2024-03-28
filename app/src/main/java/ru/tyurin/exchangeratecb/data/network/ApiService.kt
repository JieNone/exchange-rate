package ru.tyurin.exchangeratecb.data.network

import retrofit2.http.GET
import ru.tyurin.exchangeratecb.domain.model.CurrencyResponse

interface ApiService {
    @GET("daily_json.js")
    suspend fun getExchange(): CurrencyResponse
}