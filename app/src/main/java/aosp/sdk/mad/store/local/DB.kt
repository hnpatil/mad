package aosp.sdk.mad.store.local

import android.content.Context
import androidx.room.Room
import aosp.sdk.mad.store.data.Ticker

class DB(context: Context) {
    private val db = Room.databaseBuilder(context, TickerDB::class.java, "tickers").build()
    private val tickerDao = db.tickerDao()

    suspend fun getTickers(limit: Int, offset: Int): List<Ticker> {
        return tickerDao.getTickers(limit, offset)
    }

    suspend fun putTickers(tickerList: List<Ticker>) {
        tickerDao.insertAll(tickerList)
    }

    suspend fun getTickerCount(): Int {
        return tickerDao.tickers()
    }
}