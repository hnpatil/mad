package aosp.sdk.mad

import android.content.Context
import androidx.room.Room

class DB(context: Context) {
    private val db = Room.databaseBuilder(context, TickerDB::class.java, "tickers").build()
    private val tickerDao = db.tickerDao()

    fun getTickers(limit: Int, offset: Int): List<Ticker> {
        return tickerDao.getTickers(limit, offset)
    }

    fun putTickers(tickerList: List<Ticker>) {
        tickerDao.insertAll(tickerList)
    }

    fun getTickerCount(): Int {
        return tickerDao.tickers()
    }
}