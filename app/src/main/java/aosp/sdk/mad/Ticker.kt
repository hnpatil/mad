package aosp.sdk.mad

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson

@Entity(tableName = "ticker")
data class Ticker(@PrimaryKey val ticker: String,
                  @ColumnInfo(name = "name") val name: String?,
                  @ColumnInfo(name = "market") val market: String?,
                  @ColumnInfo(name = "local") val local :String?,
                  @ColumnInfo(name = "primary_exchange") val primary_exchange: String?,
                  @ColumnInfo(name = "type") val type: String?,
                  @ColumnInfo(name = "active") val active: Boolean = false): Atom() {

    companion object CREATOR : Parcelable.Creator<Ticker> {
        override fun createFromParcel(parcel: Parcel): Ticker {
            return Gson().fromJson(parcel.readString(), Ticker::class.java)
        }

        override fun newArray(size: Int): Array<Ticker?> {
            return arrayOfNulls(size)
        }
    }
}