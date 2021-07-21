package aosp.sdk.mad.store.data

import com.google.gson.Gson

data class TickerDetail(val logo: String?, val country: String?,
                        val industry: String?, val sector: String?,
                        val phone: String?, val ceo: String?,
                        val url: String?, val description: String?,
                        val name: String?, val symbol: String?) {

    override fun toString(): String {
        return gson.toJson(this)
    }

    companion object {
        private val gson = Gson()

        fun fromString(raw: String): TickerDetail {
            return gson.fromJson(raw, TickerDetail::class.java)
        }
    }
}