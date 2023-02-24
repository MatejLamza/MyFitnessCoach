package matej.lamza.mycoach.data.repo.auth

import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.AuthResult

interface AuthRepo {
    suspend fun signIn(googleCredentials: SignInCredential): AuthResult

    fun signOut()
}
