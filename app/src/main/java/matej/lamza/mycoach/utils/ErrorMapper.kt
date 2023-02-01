package matej.lamza.mycoach.utils

import android.util.Log
import com.google.android.gms.common.api.ApiException
import matej.lamza.mycoach.common.ErrorMessage
import matej.lamza.mycoach.common.exception.NoGoogleAccountFound
import matej.lamza.mycoach.common.exception.UnknownErrorException

object ErrorMapper {
    fun mapError(throwable: Throwable?): ErrorMessage {
        Log.d("LoginGoogle", "mapError: dobio sam $throwable ")
        return when (throwable) {
            is ApiException -> {
                Log.d("LoginGoogle", "mapError: tu sam")
                NoGoogleAccountFound().toErrorMessage()
            }
            else -> {
                Log.d("LoginGoogle", "mapError: else ")
                UnknownErrorException().toErrorMessage()
            }
        }
    }

    fun mapException(exception: Exception): ErrorMessage {
        return when (exception) {
            is ApiException -> NoGoogleAccountFound().toErrorMessage()
            else -> UnknownErrorException().toErrorMessage()
        }
    }
}
