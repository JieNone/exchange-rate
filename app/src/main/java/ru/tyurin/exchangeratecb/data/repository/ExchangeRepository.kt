package ru.tyurin.exchangeratecb.data.repository

import ru.tyurin.exchangeratecb.data.network.ExchangeResult


interface ExchangeRepository {
    suspend fun loadExchange() : ExchangeResult
}