package matej.lamza.mycoach.data.local.session

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import matej.lamza.mycoach.data.User


class Session(private val sharedPrefs: SharedPreferences, private val gson: Gson) {

    var user: User?
        set(value) {
            if (value != null) {
                val user = gson.toJson(value)
                sharedPrefs.edit().putString(KEY_USER, user).apply()
            } else {
                sharedPrefs.edit().remove(KEY_USER).apply()
            }
        }
        get() {
            return runCatching {
                val json = sharedPrefs.getString(KEY_USER, null)
                val type = object : TypeToken<User?>() {}.type
                gson.fromJson<User?>(json, type)
            }.getOrNull()
        }
}
