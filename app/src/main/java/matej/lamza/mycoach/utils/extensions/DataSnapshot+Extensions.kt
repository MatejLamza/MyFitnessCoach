package matej.lamza.mycoach.utils.extensions

import com.google.firebase.database.DataSnapshot

fun DataSnapshot.getStringValue(key: String): String? {
    return children.firstOrNull { it.key == key }?.value as? String
}


