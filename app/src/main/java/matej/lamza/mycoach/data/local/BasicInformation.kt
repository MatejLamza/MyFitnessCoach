package matej.lamza.mycoach.data.local

import matej.lamza.mycoach.data.local.enums.Gender

interface BasicInformation {
    val name: String
    val lastName: String
    val dateOfBirth: String
    val gender: Gender
}
