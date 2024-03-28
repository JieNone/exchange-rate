package ru.tyurin.exchangeratecb.data.network

import ru.tyurin.exchangeratecb.data.repository.ExchangeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExchangeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ExchangeRepository {

    override suspend fun loadExchange(): ExchangeResult {
        return try {
            val data = apiService.getExchange()
            ExchangeResult.Success(data)
        } catch (e: Exception) {
            ExchangeResult.Error(e.message ?: "Неизвестная ошибка")
        }
    }
}