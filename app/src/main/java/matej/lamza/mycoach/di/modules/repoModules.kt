package matej.lamza.mycoach.di.modules

import matej.lamza.mycoach.data.repo.auth.AuthRepo
import matej.lamza.mycoach.data.repo.auth.AuthRepoImpl
import matej.lamza.mycoach.data.repo.client.ClientRepo
import matej.lamza.mycoach.data.repo.client.ClientRepoImpl
import org.koin.dsl.module


val repoModule = module {

    single<ClientRepo> { ClientRepoImpl(firebase = get()) }
    single<AuthRepo> { AuthRepoImpl(firebaseAuth = get(), get(), sessionPrefs = get()) }

}
