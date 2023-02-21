package matej.lamza.mycoach.di

import android.app.Application
import matej.lamza.mycoach.di.modules.*
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
        databaseModule,
        networkModule,
        viewModelModules,
        repoModule
    )

    fun initialize() {
        koinApplication = startKoin {
            androidContext(application)
            modules(modules)
            androidLogger(Level.INFO)
        }
    }
}
