package aosp.sdk.mad

import android.content.Context
import aosp.sdk.mad.store.StocksRepo
import aosp.sdk.mad.store.local.DB
import aosp.sdk.mad.store.local.FileStore
import aosp.sdk.mad.store.local.Preferences
import aosp.sdk.mad.store.remote.PolygonService
import aosp.sdk.mad.vm.StocksVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
        PolygonService()
    }

    single {
        DB(get())
    }

    single {
        Preferences((get() as Context).getSharedPreferences("prefs", Context.MODE_PRIVATE))
    }

    single {
        FileStore(get())
    }

    single {
        StocksRepo((get() as PolygonService).getService(), get(), get(), get())
    }

    viewModel {
        StocksVM(get(), get())
    }
}