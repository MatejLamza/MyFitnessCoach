package matej.lamza.mycoach.data.local.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import matej.lamza.mycoach.data.local.enums.Gender

@Entity(tableName = "clients")
data class ClientEntity(
    @PrimaryKey(autoGenerate = false)
    val slotID: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "lastName")
    val lastName: String,

    @ColumnInfo(name = "dateOfBirth")
    val dateOfBirth: String,

    @ColumnInfo(name = "gender")
    val gender: Gender
)
