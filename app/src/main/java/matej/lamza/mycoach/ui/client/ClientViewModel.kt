package matej.lamza.mycoach.ui.client

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.repo.client.ClientRepo

private const val TIMEOUT = 100L

class ClientViewModel(private val clientRepo: ClientRepo) : ViewModel() {

    @OptIn(FlowPreview::class)
    private val _clientSlots = clientRepo.getClients().debounce(TIMEOUT)

    init {
        viewModelScope.launch {
            _clientSlots.collect {
                Log.d("ClientRepo", "tu sam: ${it.size}")
            }
        }
    }

    fun saveClient(client: Client) {
        viewModelScope.launch {
            clientRepo.addClient(client = client)
        }
    }

}
