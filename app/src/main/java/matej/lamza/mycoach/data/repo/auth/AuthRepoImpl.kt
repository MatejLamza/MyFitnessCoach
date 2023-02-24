package matej.lamza.mycoach.data.repo.auth

import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import matej.lamza.mycoach.common.FirebaseConstants
import matej.lamza.mycoach.data.local.Coach
import kotlin.coroutines.resume

class AuthRepoImpl(private val firebaseAuth: FirebaseAuth, private val firebaseDatabase: FirebaseDatabase) : AuthRepo {

    private val coroutineScope = CoroutineScope(IO)

    private val coachReference by lazy { firebaseDatabase.getReference(FirebaseConstants.COACH_REFERENCE) }

    override suspend fun signIn(googleCredentials: SignInCredential) =
        suspendCancellableCoroutine<AuthResult> { continuation ->
            val firebaseCredentials = GoogleAuthProvider.getCredential(googleCredentials.googleIdToken, null)

            firebaseAuth.signInWithCredential(firebaseCredentials)
                .addOnCompleteListener { task ->
                    continuation.resume(task.result)
                    handleAuthResult(task)
                }
                .addOnFailureListener { continuation.cancel(it) }
        }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    //region Private helper functions
    private fun handleAuthResult(task: Task<AuthResult>) {
        task.addOnSuccessListener { result ->
            if (result.additionalUserInfo?.isNewUser == true)
                coroutineScope.launch { saveCoachToFirebase(result) }
        }
    }

    private suspend fun saveCoachToFirebase(result: AuthResult) {
        withContext(IO) {
            val coach = Coach.fromAuthResult(result)
            coachReference.child(coach.uid).setValue(coach)
        }
    }
    //endregion
}
