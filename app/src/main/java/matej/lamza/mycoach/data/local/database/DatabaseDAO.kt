package matej.lamza.mycoach.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import matej.lamza.mycoach.data.local.database.models.ClientEntity

@Dao
interface DatabaseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(clientEntity: ClientEntity)

    @Query("SELECT * FROM clients")
    fun getClients(): Flow<List<ClientEntity>>

}
