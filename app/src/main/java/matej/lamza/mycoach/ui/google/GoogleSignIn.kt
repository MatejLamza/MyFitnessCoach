package matej.lamza.mycoach.ui.google

import android.app.Activity
import androidx.activity.result.ActivityResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential

fun getIDTokenFromGoogle(
    activityResult: ActivityResult,
    oneTapClient: SignInClient
): SignInCredential? {
    return if (activityResult.resultCode == Activity.RESULT_OK)
        oneTapClient.getSignInCredentialFromIntent(activityResult.data)
    else null
}

