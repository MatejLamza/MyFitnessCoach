package matej.lamza.mycoach.di.modules

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val networkModule = module {

    single { FirebaseAuth.getInstance() }
}
