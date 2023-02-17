package matej.lamza.mycoach.data.local

import matej.lamza.mycoach.data.local.enums.Sex

data class Client(
    override val name: String,
    override val lastName: String,
    override val dateOfBirth: String,
    override val sex: Sex,
    val measurements: Measurements
) : BasicInformation
