package matej.lamza.mycoach.di.modules

import android.content.Context
import android.content.SharedPreferences
import matej.lamza.mycoach.BuildConfig
import matej.lamza.mycoach.data.local.session.SessionPrefs
import matej.lamza.mycoach.data.local.session.SessionProvider
import matej.lamza.mycoach.utils.FlipperInitializer
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        androidContext().getSharedPreferences(
            BuildConfig.APPLICATION_ID,
            Context.MODE_PRIVATE
        )
    }

    single { SessionPrefs(sharedPreferences = get()) }
    single { SessionProvider(sessionPrefs = get(), firebaseAuth = get()) }
    single { FlipperInitializer(application = androidApplication()) }
}
