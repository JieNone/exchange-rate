package ru.tyurin.exchangeratecb.domain.model

import com.google.gson.annotations.SerializedName
import ru.tyurin.exchangeratecb.data.model.Currency
import java.util.Date

data class CurrencyResponse(
    @SerializedName("Date")
    val date: Date,
    @SerializedName("PreviousDate")
    val previousDate: Date,
    @SerializedName("PreviousURL")
    val previousURL: String,
    @SerializedName("Timestamp")
    val timestamp: Date,
    @SerializedName("Valute")
    val valute: Map<String, Currency>
)