package matej.lamza.mycoach.ui.client

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.repo.client.ClientRepo

class ClientViewModel(private val clientRepo: ClientRepo) : ViewModel() {

    private val _clientSlots = clientRepo.getClients().debounce(100)

    init {
        viewModelScope.launch {
            _clientSlots.collect {
                Log.d("bbb", "tu sam: ${it.size}")
            }
        }
    }

    fun saveClient(client: Client) {
        viewModelScope.launch {
            clientRepo.addClient(client = client)
        }
    }

}
