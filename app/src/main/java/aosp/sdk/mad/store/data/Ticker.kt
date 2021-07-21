package aosp.sdk.mad.store.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticker")
data class Ticker(@PrimaryKey val ticker: String,
                  @ColumnInfo(name = "name") val name: String?,
                  @ColumnInfo(name = "market") val market: String?,
                  @ColumnInfo(name = "local") val local :String?,
                  @ColumnInfo(name = "primary_exchange") val primary_exchange: String?,
                  @ColumnInfo(name = "type") val type: String?,
                  @ColumnInfo(name = "active") val active: Boolean = false)