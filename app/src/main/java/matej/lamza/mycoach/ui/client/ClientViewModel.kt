package matej.lamza.mycoach.ui.client

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.local.enums.SearchWidgetState
import matej.lamza.mycoach.data.repo.client.ClientRepo

private const val TIMEOUT = 100L

class ClientViewModel(private val clientRepo: ClientRepo) : ViewModel() {


    private val _searchWidgetState: MutableState<SearchWidgetState> = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    @OptIn(FlowPreview::class)
    private val _clientSlots = clientRepo.getClients().debounce(TIMEOUT)

    @OptIn(FlowPreview::class)
    val clients = _searchTextState
        .debounce(1000)
        .combine(_clientSlots)
        { query, clients ->
            if (query.isBlank()) clients
            else clients.filter { it.doesMatchSearchQuery(query) }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

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

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

}
