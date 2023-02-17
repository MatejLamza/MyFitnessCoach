package matej.lamza.mycoach.data.local

import matej.lamza.mycoach.data.local.enums.Sex

interface BasicInformation {
    val name: String
    val lastName: String
    val dateOfBirth: String
    val sex: Sex
}
