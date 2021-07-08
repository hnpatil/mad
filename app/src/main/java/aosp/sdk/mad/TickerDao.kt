package aosp.sdk.mad

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TickerDao {
    @Query("SELECT * FROM ticker")
    fun getAll(): List<Ticker>
    @Query("SELECT * FROM ticker limit :limit offset :offset")
    fun getTickers(limit: Int, offset: Int): List<Ticker>
    @Query("SELECT COUNT(*) FROM ticker")
    fun tickers(): Int
    @Insert
    fun insertAll(tickers: List<Ticker>)
    @Delete
    fun delete(ticker: Ticker)
}