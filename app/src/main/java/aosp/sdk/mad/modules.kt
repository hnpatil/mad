package aosp.sdk.mad

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
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
        StocksRepo((get() as PolygonService).getService(), get(), get())
    }

    viewModel {
        StocksVM(it.get<SavedStateHandle>() as SavedStateHandle, get(), get(), GlobalScope)
    }
}