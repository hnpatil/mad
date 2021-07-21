package aosp.sdk.mad.store.local

import android.content.SharedPreferences

class Preferences(private val preferences: SharedPreferences) {

    fun getNextTickerCursor() : String? {
        return preferences.getString("next_ticker_cursor", null)
    }

    fun updateNextTickerCursor(cursor: String?) {
        preferences.edit().putString("next_ticker_cursor", cursor).apply()
    }
}