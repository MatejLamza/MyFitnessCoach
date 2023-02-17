package matej.lamza.mycoach.common.exception

import matej.lamza.mycoach.R
import matej.lamza.mycoach.common.ErrorMessage
import java.util.*

class UnknownErrorException : Exception() {
    fun toErrorMessage() =
        ErrorMessage(UUID.randomUUID().mostSignificantBits, R.string.unknown_error)
}
