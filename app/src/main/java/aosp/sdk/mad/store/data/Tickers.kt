package aosp.sdk.mad.store.data

data class Tickers(val results: List<Ticker>?,
              val status: String?,
              val request_id: String?,
              val count: Int = 0,
              val next_url: String? = null)