package matej.lamza.mycoach.data.local

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Client(
    override val name: String? = null,
    override val lastName: String? = null,
    override val dateOfBirth: String? = null,
) : BasicInformation
