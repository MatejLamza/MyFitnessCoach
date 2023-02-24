package matej.lamza.mycoach.di.modules

import matej.lamza.mycoach.ui.client.ClientViewModel
import matej.lamza.mycoach.ui.signin.LoginViewModel
import matej.lamza.mycoach.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { SplashViewModel(sessionPrefs = get(), get()) }
    viewModel { LoginViewModel(sessionProvider = get(), get()) }
    viewModel { ClientViewModel(clientRepo = get()) }
}
