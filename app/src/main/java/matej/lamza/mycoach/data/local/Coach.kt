package matej.lamza.mycoach.data.local

import matej.lamza.mycoach.data.local.enums.Gender

data class Coach(
    override val name: String,
    override val lastName: String,
    override val dateOfBirth: String,
    override val gender: Gender,
    val measurements: Measurements,
) : BasicInformation
