package aosp.sdk.mad

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.parameter.DefinitionParameters

object DI {

    lateinit var koin: Koin

    fun start(application: Application, modules: List<Module>) {
        koin = startKoin {
            androidContext(application)
            modules(modules)
        }.koin
    }

    inline fun <reified T> get() : T {
        return koin.get()
    }

    inline fun <reified T> get(params: DefinitionParameters): T {
        return koin.get { params }
    }

    inline  fun <reified T> inject() : Lazy<T> {
        return koin.inject()
    }

    inline  fun <reified T> inject(params: DefinitionParameters) : Lazy<T> {
        return koin.inject {params}
    }
}