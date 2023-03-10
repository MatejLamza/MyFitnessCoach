package matej.lamza.mycoach.di

import android.app.Application
import matej.lamza.mycoach.di.modules.appModule
import matej.lamza.mycoach.di.modules.networkModule
import matej.lamza.mycoach.di.modules.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

class MyCoachDI(private val application: Application) {
    private lateinit var koinApplication: KoinApplication
    private val modules: List<Module> = listOf(
        appModule,
        networkModule,
        viewModelModules
    )

    fun initialize() {
        koinApplication = startKoin {
            androidContext(application)
            modules(modules)
            androidLogger(Level.INFO)
        }
    }
}
