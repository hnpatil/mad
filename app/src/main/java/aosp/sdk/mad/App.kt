package aosp.sdk.mad

import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DI.start(this, listOf(appModule))
    }
}