package matej.lamza.mycoach.common.exception

import matej.lamza.mycoach.R
import matej.lamza.mycoach.common.ErrorMessage
import java.util.*

class NoGoogleAccountFound : Exception() {
    fun toErrorMessage(): ErrorMessage =
        ErrorMessage(UUID.randomUUID().mostSignificantBits, R.string.error_missing_google_credentials)
}
