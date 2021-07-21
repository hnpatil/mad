package aosp.sdk.mad.store

import android.net.Uri
import aosp.sdk.mad.store.local.DB
import aosp.sdk.mad.store.local.Preferences
import aosp.sdk.mad.store.data.Ticker
import aosp.sdk.mad.store.data.TickerDetail
import aosp.sdk.mad.store.local.FileStore
import aosp.sdk.mad.store.remote.Polygon
import java.io.IOException

class StocksRepo(private val polygon: Polygon, private val db: DB,
                 private val preferences: Preferences, private val fileStore: FileStore) {

    suspend fun getTickers(page: Int, pageSize: Int): List<Ticker> {
        val offset = page * pageSize
        val localTickers = db.getTickerCount()
        return if (localTickers > offset) {
            db.getTickers(pageSize, offset)
        } else {
            val cursor = preferences.getNextTickerCursor()
            val response = if (cursor == null) {
                polygon.tickers()
            } else {
                polygon.tickers(cursor = cursor)
            }
            val tickers = response.body()
            tickers?.results?.let { db.putTickers(it) }
            tickers?.next_url?.let { preferences.updateNextTickerCursor(getNextCursor(it)) }
            db.getTickers(pageSize, offset)
        }
    }

    private fun getNextCursor(url: String) : String? {
        return Uri.parse(url).getQueryParameter("cursor")
    }

    suspend fun getTickerDetail(ticker: String): TickerDetail? {
        return try {
            var tickerDetails = fileStore.fetchTickerDetail(ticker)
            if (tickerDetails == null) {
                tickerDetails = polygon.tickerDetail(ticker).body()
                tickerDetails?.let { fileStore.saveTickerDetails(it) }
            }
            return tickerDetails
        } catch (e: IOException) {
            null
        }
    }
}