package matej.lamza.mycoach.di.modules

import matej.lamza.mycoach.data.repo.client.ClientRepo
import matej.lamza.mycoach.data.repo.client.ClientRepoImpl
import org.koin.dsl.module


val repoModule = module {

    single<ClientRepo> { ClientRepoImpl() }
}
