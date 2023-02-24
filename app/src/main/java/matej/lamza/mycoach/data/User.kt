package matej.lamza.mycoach.data

import com.google.firebase.auth.AuthResult

data class User(
    val email: String = "",
    val displayName: String = "",
    val isLoggedIn: Boolean = false
)

fun AuthResult.toDomainUser() =
    with(this) {
        User(
            email = user?.email ?: "",
            displayName = user?.displayName ?: ""
        )
    }
