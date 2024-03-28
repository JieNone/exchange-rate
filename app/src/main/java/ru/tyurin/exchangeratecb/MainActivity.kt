package ru.tyurin.exchangeratecb

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.tyurin.exchangeratecb.data.network.ExchangeResult
import ru.tyurin.exchangeratecb.ui.stateholders.ExchangeViewModel
import ru.tyurin.exchangeratecb.ui.view.CurrencyAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var exchangeViewModel: ExchangeViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var lastUpdatedTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        (applicationContext as MyApp).appComponent.inject(this)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.errorTextView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        lastUpdatedTextView = findViewById(R.id.lastUpdatedTextView)

        exchangeViewModel.exchangeList.observe(this) { result ->
            when (result) {
                is ExchangeResult.Success -> {
                    val currencyResponse = result.data
                    val currencyList = currencyResponse.valute.values.toList()
                    val adapter = CurrencyAdapter(currencyList)
                    recyclerView.adapter = adapter
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                    errorTextView.visibility = View.GONE

                    val lastUpdated = "Last Updated: ${result.data.previousDate}"
                    lastUpdatedTextView.text = lastUpdated
                    lastUpdatedTextView.visibility = View.VISIBLE
                }
                is ExchangeResult.Error -> {
                    val errorMessage = result.errorMessage
                    errorTextView.text = errorMessage
                    errorTextView.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
                is ExchangeResult.Loading -> {
                    recyclerView.visibility = View.GONE
                    errorTextView.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }
}


