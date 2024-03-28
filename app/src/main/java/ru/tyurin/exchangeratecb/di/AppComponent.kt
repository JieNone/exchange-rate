package ru.tyurin.exchangeratecb.di

import dagger.Component
import ru.tyurin.exchangeratecb.MainActivity
import ru.tyurin.exchangeratecb.ui.stateholders.ExchangeViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModules::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(viewModel: ExchangeViewModel)
}