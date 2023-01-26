package matej.lamza.mycoach.data

import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.AuthResult

data class User(
    val email: String = "",
    val displayName: String = ""
)

fun AuthResult.toDomainUser() =
    with(this) {
        User(
            email = user?.email ?: "",
            displayName = user?.displayName ?: ""
        )
    }
