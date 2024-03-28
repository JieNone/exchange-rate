package ru.tyurin.exchangeratecb.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tyurin.exchangeratecb.data.network.ApiService
import ru.tyurin.exchangeratecb.data.network.ExchangeRepositoryImpl
import ru.tyurin.exchangeratecb.data.repository.ExchangeRepository
import javax.inject.Singleton


@Module
object AppModules {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbr-xml-daily.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): ExchangeRepository {
        return ExchangeRepositoryImpl(apiService)
    }
}