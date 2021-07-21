package aosp.sdk.mad.store.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import aosp.sdk.mad.store.data.Ticker

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker")
    suspend fun getAll(): List<Ticker>
    @Query("SELECT * FROM ticker limit :limit offset :offset")
    suspend fun getTickers(limit: Int, offset: Int): List<Ticker>
    @Query("SELECT COUNT(*) FROM ticker")
    suspend fun tickers(): Int
    @Insert
    suspend fun insertAll(tickers: List<Ticker>)
    @Delete
    suspend fun delete(ticker: Ticker)
}