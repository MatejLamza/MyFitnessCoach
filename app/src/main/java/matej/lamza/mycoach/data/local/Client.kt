package matej.lamza.mycoach.data.local

import com.google.firebase.database.IgnoreExtraProperties
import matej.lamza.mycoach.data.local.enums.Gender

@IgnoreExtraProperties
data class Client(
    val slotID: String,
    override val name: String,
    override val lastName: String,
    override val dateOfBirth: String,
    override val gender: Gender
) : BasicInformation
