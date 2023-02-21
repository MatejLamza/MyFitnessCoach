package matej.lamza.mycoach.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import matej.lamza.mycoach.BuildConfig
import org.koin.dsl.module

val networkModule = module {

    single { FirebaseAuth.getInstance() }
    single { Firebase.database(BuildConfig.FIREBASE).apply { setPersistenceEnabled(true) } }
}
