package matej.lamza.mycoach.data.local

import com.google.firebase.auth.AuthResult
import com.google.firebase.database.IgnoreExtraProperties
import matej.lamza.mycoach.common.FirebaseConstants

@IgnoreExtraProperties
data class Coach(
    val uid: String,
    val name: String,
    val lastName: String
) {
    companion object {
        fun fromAuthResult(result: AuthResult): Coach {
            require(result.user != null && result.additionalUserInfo != null)
            return Coach(
                uid = result.user!!.uid,
                name = result.additionalUserInfo?.profile?.get(FirebaseConstants.NAME) as? String ?: "",
                lastName = result.additionalUserInfo?.profile?.get(FirebaseConstants.LAST_NAME) as? String ?: ""
            )
        }
    }
}
