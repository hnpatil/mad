package aosp.sdk.mad

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Ticker::class], version = 1)
abstract class TickerDB: RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}