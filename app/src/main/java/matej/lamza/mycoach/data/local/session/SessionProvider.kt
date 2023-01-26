package matej.lamza.mycoach.data.local.session

import android.util.Log
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import matej.lamza.mycoach.data.toDomainUser

class SessionProvider(private val sessionPrefs: SessionPrefs, private val firebaseAuth: FirebaseAuth) {

    companion object {
        const val TAG = "SessionProvider"
    }

    suspend fun login(
        googleCredentials: SignInCredential
    ) {
        withContext(IO) {
            Log.d("bbb", "login: ")
            val firebaseCredentials = GoogleAuthProvider.getCredential(googleCredentials.googleIdToken, null)
            firebaseAuth.signInWithCredential(firebaseCredentials)
                .addOnSuccessListener { launch { sessionPrefs.setUser(it.toDomainUser()) } }
                .addOnFailureListener { Log.e(TAG, "Failure: $it ", it); throw it }
        }
    }


    suspend fun logout() {
        withContext(IO) { firebaseAuth.signOut() }
    }

    suspend fun invalidate() {
        withContext(IO) { sessionPrefs.clear() }
    }
}
