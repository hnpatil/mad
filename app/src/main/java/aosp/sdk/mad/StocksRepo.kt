package aosp.sdk.mad

import android.net.Uri
import java.io.IOException

class StocksRepo(private val polygon: Polygon, private val db: DB, private val preferences: Preferences) {

    fun getTickers(page: Int, pageSize: Int): List<Ticker>? {
        val offset = page * pageSize
        val localTickers = db.getTickerCount()
        return if (localTickers > offset) {
            db.getTickers(pageSize, offset)
        } else {
            val cursor = preferences.getNextTickerCursor()
            val response = if (cursor == null) {
                polygon.tickers().execute()
            } else {
                polygon.tickers(cursor = cursor).execute()
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

    fun getTickerDetail(ticker: String): TickerDetail? {
        return try {
            polygon.tickerDetail(ticker).execute()?.body()
        } catch (e: IOException) {
            null
        }
    }
}