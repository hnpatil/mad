package aosp.sdk.mad

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.Gson

abstract class Atom: Parcelable {
    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(Gson().toJson(this))
    }

    override fun describeContents(): Int {
        return 0
    }
}