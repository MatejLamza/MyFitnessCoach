package matej.lamza.mycoach.data.local.session

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import matej.lamza.mycoach.data.User
import matej.lamza.mycoach.utils.extensions.getObject
import matej.lamza.mycoach.utils.extensions.observeObject
import matej.lamza.mycoach.utils.extensions.setObject

const val KEY_AUTH_TOKEN = "auth_token"
const val KEY_USER = "key_user"

class SessionPrefs(private val sharedPreferences: SharedPreferences) {

    // region User
    suspend fun setUser(value: User) = sharedPreferences.setObject(KEY_USER, value)
    fun getUser(): User? = sharedPreferences.getObject<User?>(KEY_USER, null)
    fun observeUser(): Flow<User?> = sharedPreferences.observeObject(KEY_USER)
    //endregion

    suspend fun clear() {
        withContext(IO) {
            sharedPreferences.edit(commit = true) {
                sharedPreferences.all.keys.forEach {
                    remove(it)
                }
            }
        }
    }
}
