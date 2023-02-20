package matej.lamza.mycoach.data.repo.client

import kotlinx.coroutines.flow.Flow
import matej.lamza.mycoach.data.local.Client

interface ClientRepo {

    suspend fun addClient(client: Client)

    fun getClients(): Flow<List<Client>>

    fun getClient(clientSlotID: String): Flow<Client?>
}
