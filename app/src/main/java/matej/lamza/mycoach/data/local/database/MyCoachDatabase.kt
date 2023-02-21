package matej.lamza.mycoach.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import matej.lamza.mycoach.data.local.database.models.ClientEntity

@Database(entities = [ClientEntity::class], version = 2, exportSchema = false)
abstract class MyCoachDatabase : RoomDatabase() {
    abstract fun provideDAO(): DatabaseDAO
}
