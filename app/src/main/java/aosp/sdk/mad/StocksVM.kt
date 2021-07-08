package aosp.sdk.mad

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StocksVM(
    private val savedStateHandle: SavedStateHandle,
    application: Application,
    private val stocksRepo: StocksRepo,
    private val viewModelScope: CoroutineScope
) : AndroidViewModel(application) {

    private val tickerStream = Pager(PagingConfig(10)) { TickerPagingSource(stocksRepo, 10) }.flow
    val adapter = StocksListAdapter { ticker: Ticker -> updateSelectedTicker(ticker)}

    val selectedTicker: MutableLiveData<Ticker> get() = savedStateHandle.getLiveData("selectedTicker", null)
    val tickerDetail: MutableLiveData<TickerDetail> = MutableLiveData()
    val loadingTickerDetail = MutableLiveData<Boolean>(false)

    fun fetchTickers() {
        viewModelScope.launch { tickerStream.collectLatest { adapter.submitData(it) } }
    }

    fun updateSelectedTicker(ticker: Ticker?) {
        selectedTicker.value = ticker
        fetchTickerDetail(ticker?.ticker)
    }

    private fun fetchTickerDetail(ticker: String?) {
        viewModelScope.launch {
            loadingTickerDetail.postValue(true)
            val details = if(ticker== null) null else  stocksRepo.getTickerDetail(ticker)
            loadingTickerDetail.postValue(false)
            tickerDetail.postValue(details)
        }
    }
}