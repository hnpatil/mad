package aosp.sdk.mad.store.local

import androidx.room.Database
import androidx.room.RoomDatabase
import aosp.sdk.mad.store.data.Ticker

@Database(entities = [Ticker::class], version = 1)
abstract class TickerDB: RoomDatabase() {
    abstract fun tickerDao(): TickerDao
}