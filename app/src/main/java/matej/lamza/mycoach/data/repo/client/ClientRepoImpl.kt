package matej.lamza.mycoach.data.repo.client

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import matej.lamza.mycoach.common.FirebaseConstants
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.utils.mappers.mapToClientDomain

const val TAG = "ClientRepo"

class ClientRepoImpl(private val firebase: FirebaseDatabase) : ClientRepo {

    private val coroutineScope = CoroutineScope(IO)

    private val clientsReference by lazy { firebase.getReference(FirebaseConstants.CLIENT_REFERENCE) }

    override suspend fun addClient(client: Client) {
        withContext(IO) {
            clientsReference.child(client.slotID).setValue(client)
                .addOnFailureListener { Log.e("bbb", "addClient: fail", it) }
                .addOnSuccessListener { Log.d("bbb", "addClient: ") }
        }
    }

    override fun getClients(): Flow<List<Client>> = callbackFlow {
        val clientEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                coroutineScope.launch {
                    Log.d(TAG, "onDataChange: $snapshot")
                    trySendBlocking(snapshot.mapToClientDomain())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled: $error")
                trySendBlocking(emptyList())
            }
        }
        clientsReference.addValueEventListener(clientEventListener)
        awaitClose { clientsReference.removeEventListener(clientEventListener) }
    }


    override fun getClient(clientSlotID: String): Flow<Client?> =
        getClients().map { it.find { client -> client.slotID == clientSlotID } }

}
