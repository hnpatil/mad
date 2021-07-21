package aosp.sdk.mad.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import aosp.sdk.mad.adapters.StocksListAdapter
import aosp.sdk.mad.adapters.TickerPagingSource
import aosp.sdk.mad.store.StocksRepo
import aosp.sdk.mad.store.data.Ticker
import aosp.sdk.mad.store.data.TickerDetail
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StocksVM(
    application: Application,
    private val stocksRepo: StocksRepo
) : AndroidViewModel(application) {

    private val tickerStream = Pager(PagingConfig(10)) { TickerPagingSource(stocksRepo, 10) }.flow
    val adapter = StocksListAdapter { ticker: Ticker -> updateSelectedTicker(ticker)}

    val selectedTicker: MutableLiveData<Ticker> = MutableLiveData()
    val tickerDetail: MutableLiveData<TickerDetail> = MutableLiveData()
    val loadingTickerDetail = MutableLiveData<Boolean>(false)

    fun fetchTickers() {
        viewModelScope.launch { tickerStream.collectLatest { adapter.submitData(it) } }
    }

    fun updateSelectedTicker(ticker: Ticker?) {
        selectedTicker.value = ticker
        viewModelScope.launch { fetchTickerDetail(ticker?.ticker) }
    }

    private suspend fun fetchTickerDetail(ticker: String?) {
        loadingTickerDetail.value = true
        val details = if(ticker== null) null else  stocksRepo.getTickerDetail(ticker)
        loadingTickerDetail.value = false
        tickerDetail.value = details
    }
}