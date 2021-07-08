package aosp.sdk.mad

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson

class Tickers(val results: List<Ticker>?,
                   val status: String?,
                   val request_id: String?,
                   val count: Int = 0,
                   val next_url: String? = null): Atom() {

    companion object CREATOR : Parcelable.Creator<Tickers> {
        override fun createFromParcel(parcel: Parcel): Tickers {
            return Gson().fromJson(parcel.readString(), Tickers::class.java)
        }

        override fun newArray(size: Int): Array<Tickers?> {
            return arrayOfNulls(size)
        }
    }
}