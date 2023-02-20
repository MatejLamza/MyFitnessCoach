package matej.lamza.mycoach.utils.mappers

import com.google.firebase.database.DataSnapshot
import matej.lamza.mycoach.common.exception.UnknownErrorException
import matej.lamza.mycoach.data.local.Client
import matej.lamza.mycoach.data.local.enums.Gender
import matej.lamza.mycoach.utils.extensions.getStringValue

fun DataSnapshot.mapToClientDomain(): List<Client> {
    val clients = arrayListOf<Client>()
    children.forEach { clientSnapshot ->
        clients.add(
            Client(
                slotID = clientSnapshot.getStringValue("slotID") ?: "",
                name = clientSnapshot.getStringValue("name") ?: "",
                lastName = clientSnapshot.getStringValue("lastName") ?: "",
                gender = clientSnapshot.mapGender("gender"),
                dateOfBirth = getStringValue("dateOfBirth") ?: ""
            )
        )
    }
    return clients.toList()
}

private fun DataSnapshot.mapGender(key: String): Gender =
    when (getStringValue(key)?.uppercase()) {
        "FEMALE" -> Gender.FEMALE
        "MALE" -> Gender.MALE
        else -> throw UnknownErrorException()
    }



